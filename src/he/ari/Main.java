package he.ari;

import he.ari.concurrency.MultithreadedExecutor;
import he.ari.templates.ExecutorFactory;
import he.ari.templates.SimulationTemplates;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MultithreadedExecutor executor = ExecutorFactory.defaultModel();
        executor.executeSafely();
        executor.summarize();
    }
}
