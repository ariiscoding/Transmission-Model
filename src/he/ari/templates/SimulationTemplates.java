package he.ari.templates;

import he.ari.model.Simulator;
import he.ari.model.Virus;
import he.ari.tools.Utils;

public class SimulationTemplates {
    public static Simulator.Builder defaultModel() {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(50);
        builder.setPeoplePerCommunities(25);
        builder.setHospitalCapacity(Utils.calcHospitalCapacity(builder, 0.08));
        builder.setContagion(new Virus(0.0027, 0.0015));
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
        builder.setHospitalCapacity(Utils.calcHospitalCapacity(builder, 0.08));
        builder.setContagion(new Virus(0.012, 0.03));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        return builder;
    }

    public static Simulator.Builder newJerseyModel() {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(50);
        builder.setPeoplePerCommunities(25);
        builder.setHospitalCapacity(Utils.calcHospitalCapacity(builder, 0.08));
        builder.setContagion(new Virus(0.011, 0.023));
        builder.setIntercommunityMobility(0.3);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        return builder;
    }

    public static Simulator.Builder californiaModel() {
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(75);
        builder.setPeoplePerCommunities(55);
        builder.setHospitalCapacity(Utils.calcHospitalCapacity(builder, 0.15));
        builder.setContagion(new Virus(0.0005, 0.015));
        builder.setIntercommunityMobility(0.1);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        return builder;
    }

    public static Simulator.Builder chinaModel() {
        //this model is currently a little slow
        Simulator.Builder builder = new Simulator.Builder();
        builder.setNumberOfCommunities(100);
        builder.setPeoplePerCommunities(60);
        builder.setHospitalCapacity(Utils.calcHospitalCapacity(builder, 0.2));
        builder.setContagion(new Virus(0.00005, 0.015));
        builder.setIntercommunityMobility(0.002);
        builder.setReportIterationStats(false);
        builder.setInitialInfected(10);
        builder.setMinIteration(10);
        builder.setPrintStartAndFinishStats(false);

        return builder;
    }
}
