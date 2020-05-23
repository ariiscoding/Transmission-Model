package he.ari.templates;

import he.ari.concurrency.MultithreadedExecutor;

public class ExecutorFactory {
    public static MultithreadedExecutor defaultModel() {
        return new MultithreadedExecutor(SimulationTemplates.defaultModel(), 300, 10);
    }

    public static MultithreadedExecutor newYorkModel() {
        return new MultithreadedExecutor(SimulationTemplates.newYorkModel(), 300, 15);
    }

    public static MultithreadedExecutor newJerseyModel() {
        return new MultithreadedExecutor(SimulationTemplates.newJerseyModel(), 300, 15);
    }

    public static MultithreadedExecutor californiaModel() {
        return new MultithreadedExecutor(SimulationTemplates.californiaModel(), 300, 15);
    }
}
