package he.ari;

import he.ari.concurrency.MultithreadedExecutor;
import he.ari.concurrency.ThreadStats;
import he.ari.templates.Templates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MultithreadedExecutor multithreadedExecutor = new MultithreadedExecutor(Templates.defaultModel(),300);
        multithreadedExecutor.execute();
        multithreadedExecutor.summarize();
    }
}
