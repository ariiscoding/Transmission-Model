package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;
import he.ari.model.Virus;

import java.util.Map;

public class ModelThread implements Runnable{
    Simulator.Builder blueprint;
    Map<Integer, FinalSnapshot> database;
    int index;

    public ModelThread(Simulator.Builder blueprint, Map<Integer, FinalSnapshot> database, int index) {
        this.blueprint = blueprint;
        this.database = database;
        this.index = index;
    }

    @Override
    public void run() {
        Simulator simulator = blueprint.build();
        database.put(index, simulator.simulate());
    }
}
