package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadManager {
    private Simulator.Builder blueprint;
    private int iterations;
    private Map<Integer, FinalSnapshot> database;
    private List<Thread> threadList;

    public ThreadManager(Simulator.Builder blueprint, int iterations) {
        this.blueprint = blueprint;
        this.iterations = iterations;
        this.database = new HashMap<>();
        threadList = new ArrayList<>();
    }

    public void run() {
        database.clear();
        threadList.clear();
        for (int i = 0; i < iterations; i++) {
            Thread cur = new Thread(new ModelThread(blueprint, database, i));
            cur.setName(Integer.toString(i));
            threadList.add(cur);
            cur.start();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("*************************************************ALL THREADS FINISHED*************************************************");
    }

    public ThreadStats summarize() {
        ThreadStats stats = new ThreadStats(database);
        stats.summarize();
        return stats;
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

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public Map<Integer, FinalSnapshot> getDatabase() {
        return database;
    }
}
