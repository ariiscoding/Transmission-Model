package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;

import java.util.HashMap;
import java.util.Map;

public class ThreadManager {
    private Simulator.Builder blueprint;
    private int iterations;
    private Map<Integer, FinalSnapshot> database;

    public ThreadManager(Simulator.Builder blueprint, int iterations) {
        this.blueprint = blueprint;
        this.iterations = iterations;
        this.database = new HashMap<>();
    }

    public void run() {
        database.clear();
        for (int i = 0; i < iterations; i++) {
            Thread cur = new Thread(new ModelThread(blueprint, database, i));
            cur.setName(Integer.toString(i));
            cur.start();
        }
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
