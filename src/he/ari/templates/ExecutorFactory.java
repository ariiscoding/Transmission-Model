package he.ari.templates;

import he.ari.concurrency.MultithreadedExecutor;

public class ExecutorFactory {
    public static MultithreadedExecutor defaultModel() {
        return new MultithreadedExecutor(SimulationTemplates.defaultModel(), 300, 10);
    }
}
