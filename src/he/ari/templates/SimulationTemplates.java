package he.ari.templates;

import he.ari.model.Simulator;
import he.ari.model.Virus;
import he.ari.tools.Utils;

public class SimulationTemplates {
    public static Simulator.Builder defaultModel() {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(50);
        builder.setPeoplePerCommunities(25);
        builder.setHospitalCapacity(5);
        builder.setContagion(new Virus(0.001, 0.0015));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        return builder;
    }

    public static Simulator.Builder newYorkModel() {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(50);
        builder.setPeoplePerCommunities(25);
        builder.setHospitalCapacity(Utils.calcHospitalCapacity(builder, 0.8));
        builder.setContagion(new Virus(0.015, 0.03));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        return builder;
    }
}
