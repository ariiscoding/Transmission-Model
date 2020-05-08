package he.ari;

import he.ari.concurrency.ModelThread;
import he.ari.concurrency.ThreadManager;
import he.ari.model.FinalSnapshot;
import he.ari.model.Simulator;
import he.ari.model.Virus;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, FinalSnapshot> database = new HashMap<>();

        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(100);
        builder.setPeoplePerCommunities(50);
        builder.setHospitalCapacity(5);
        builder.setContagion(new Virus(0.001, 0.0001));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        ThreadManager threadManager = new ThreadManager(builder, 20);
        threadManager.run();
        threadManager.summarize();
    }
}
