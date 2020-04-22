package he.ari;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(2);
        builder.setPeoplePerCommunities(5);
        builder.setHospitalCapacity(10);
        builder.setContagion(new Virus(0.8, 0.1));
        builder.setIntercommunityMobility(0.5);
        Simulator simulator = builder.build();

        simulator.iterate();
    }
}
