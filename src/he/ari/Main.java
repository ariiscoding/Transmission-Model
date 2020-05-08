package he.ari;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(10);
        builder.setPeoplePerCommunities(10);
        builder.setHospitalCapacity(500);
        builder.setContagion(new Virus(0.015, 0.001));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(true);
        Simulator simulator = builder.build();

        simulator.simulate();
    }
}
