package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;

import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadUnit implements Callable<FinalSnapshot> {
    Simulator.Builder blueprint;

    public ThreadUnit(Simulator.Builder blueprint) {
        this.blueprint = blueprint;
    }

    @Override
    public FinalSnapshot call(){
        Simulator simulator = blueprint.build();
        return simulator.simulate();
    }
}
