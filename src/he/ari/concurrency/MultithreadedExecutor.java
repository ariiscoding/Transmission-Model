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

    public MultithreadedExecutor(Simulator.Builder blueprint, int iterations) {
        this.threadPoolSize = 8;
        this.timeOutSeconds = 10;
        this.blueprint = blueprint;
        this.iterations = iterations;
        this.results = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public int execute() throws InterruptedException, ExecutionException {
        //return the number of simulations that failed (mostly due to timeout)

        results.clear();
        int timedOutThreads = 0;

        List<ModelThread2> threads = new ArrayList<>();
        for (int i = 0; i < iterations; i++) threads.add(new ModelThread2(blueprint));

        List<Future<FinalSnapshot>> futures = executorService.invokeAll(threads, timeOutSeconds, TimeUnit.SECONDS);
        executorService.shutdown();

        for (Future<FinalSnapshot> future : futures) {
            if (!future.isCancelled()) results.add(future.get());
            else timedOutThreads++;
        }

        System.out.println("*************************************************ALL THREADS FINISHED*************************************************");
        return timedOutThreads;
    }

    public ThreadStats summarize() {
        ThreadStats stats = new ThreadStats(results);
        stats.summarize();
        return stats;
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
