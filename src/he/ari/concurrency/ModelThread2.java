package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;

import java.util.Map;
import java.util.concurrent.Callable;

public class ModelThread2 implements Callable<FinalSnapshot> {
    Simulator.Builder blueprint;

    public ModelThread2 (Simulator.Builder blueprint) {
        this.blueprint = blueprint;
    }

    @Override
    public FinalSnapshot call(){
        Simulator simulator = blueprint.build();
        return simulator.simulate();
    }
}
