package he.ari;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(100);
        builder.setPeoplePerCommunities(1000);
        builder.setHospitalCapacity(100);
        builder.setContagion(new Virus(0.95, 0.0001));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(true);
        Simulator simulator = builder.build();

        simulator.simulate();
    }
}
