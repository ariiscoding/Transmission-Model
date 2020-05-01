package he.ari;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(1);
        builder.setPeoplePerCommunities(100);
        builder.setHospitalCapacity(100);
        builder.setContagion(new Virus(0.001, 0.0001));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(true);
        builder.setInitialInfected(10);
        Simulator simulator = builder.build();

        simulator.simulate();
    }
}
