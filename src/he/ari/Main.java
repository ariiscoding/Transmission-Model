package he.ari;

import he.ari.concurrency.MultithreadedExecutor;
import he.ari.model.Simulator;
import he.ari.templates.ExecutorFactory;
import he.ari.templates.SimulationTemplates;
import he.ari.tools.Utils;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MultithreadedExecutor executor = ExecutorFactory.defaultModel();
        executor.executeSafely();
        executor.summarize();
    }
}
