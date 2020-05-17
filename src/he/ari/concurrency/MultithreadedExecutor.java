package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadedExecutor {
    private Simulator.Builder blueprint;
    private int iterations;
    private List<FinalSnapshot> results;
    private ExecutorService executorService;
    private int threadPoolSize;
    private int timeOutSeconds;
    private ThreadStats lastStats;

    public MultithreadedExecutor(Simulator.Builder blueprint, int iterations, int timeOutSeconds) {
        this.threadPoolSize = 8;
        this.timeOutSeconds = timeOutSeconds;
        this.blueprint = blueprint;
        this.iterations = iterations;
        this.results = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        lastStats = null;
    }

    public MultithreadedExecutor (Simulator.Builder blueprint, int iterations) {
        this(blueprint, iterations, 10);
    }

    public MultithreadedExecutor(Simulator.Builder blueprint) {
        this(blueprint, 200);
    }

    public ThreadStats executeSafely() {
        ThreadStats output = null;
        try {
            output = execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return output;
        }
    }

    public ThreadStats execute() throws InterruptedException, ExecutionException {
        //return the number of simulations that failed (mostly due to timeout)

        System.out.println("*************************************************EXECUTION STARTS*************************************************");
        results.clear();
        int timedOutThreads = 0;

        List<ThreadUnit> threads = new ArrayList<>();
        for (int i = 0; i < iterations; i++) threads.add(new ThreadUnit(blueprint));

        List<Future<FinalSnapshot>> futures = executorService.invokeAll(threads, timeOutSeconds, TimeUnit.SECONDS);
        executorService.shutdown();

        for (Future<FinalSnapshot> future : futures) {
            if (!future.isCancelled()) results.add(future.get());
            else timedOutThreads++;
        }

        System.out.println("*************************************************ALL THREADS FINISHED*************************************************");
        generateSummaryStats(timedOutThreads);
        return this.lastStats;
    }

    private ThreadStats generateSummaryStats(int timedOutThreads) {
        this.lastStats = new ThreadStats(results, timedOutThreads);
        return this.lastStats;
    }

    public ThreadStats summarize() {
        if (lastStats==null) {
            System.out.println("No history of execution.");
            return null;
        }
        lastStats.summarize();
        return lastStats;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public boolean setThreadPoolSize(int newSize) {
        if (newSize <= 0 || newSize >= 3000) return false;
        threadPoolSize = newSize;
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        return true;
    }

    public Simulator.Builder getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Simulator.Builder blueprint) {
        this.blueprint = blueprint;
    }

    public int getIterations() {
        return iterations;
    }

    public boolean setIterations(int iterations) {
        if (iterations <= 0) return false;
        this.iterations = iterations;
        return true;
    }

    public int getTimeOutSeconds() {
        return timeOutSeconds;
    }

    public boolean setTimeOutSeconds(int timeOutSeconds) {
        if (timeOutSeconds <= 0) return false;
        this.timeOutSeconds = timeOutSeconds;
        return true;
    }
}
