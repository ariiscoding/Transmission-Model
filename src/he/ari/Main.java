package he.ari;

import he.ari.concurrency.ModelThread;
import he.ari.concurrency.ThreadManager;
import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;
import he.ari.model.Virus;
import he.ari.templates.Templates;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ThreadManager threadManager = new ThreadManager(Templates.defaultModel(), 50);
        threadManager.run();
        threadManager.summarize();
    }
}
