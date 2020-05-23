package he.ari.templates;

import he.ari.concurrency.MultithreadedExecutor;

public class ExecutorFactory {
    public static MultithreadedExecutor defaultModel() {
        return new MultithreadedExecutor(SimulationTemplates.defaultModel(), 300, 10);
    }

    public static MultithreadedExecutor newYorkModel() {
        return new MultithreadedExecutor(SimulationTemplates.newYorkModel(), 300, 15);
    }
}
