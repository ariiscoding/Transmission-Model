package he.ari.concurrency;

import he.ari.model.FinalSnapshot;

import java.util.Map;

public class ThreadStats {
    private long simulationCount;
    private long totalTime;
    private long population;
    private long healthy;
    private long totalInfected;
    private long cured;
    private long deceased;


    public ThreadStats (Map<Integer, FinalSnapshot> database) {
        this.simulationCount = database.size();
        totalTime = 0;
        this.population = 0;
        this.healthy = 0;
        this.totalInfected = 0;
        this.cured = 0;
        this.deceased = 0;

        for (FinalSnapshot cur : database.values()) {
            population += cur.getPopulation();
            totalTime += cur.getEndTime();
            healthy += cur.getHealthy();
            totalInfected += cur.getInfected();
            cured += cur.getCured();
            deceased += cur.getDeceased();
        }
    }

    public void summarize() {
        System.out.println("****************************************************************");
        System.out.println("Total number of simulations: " + simulationCount);
        System.out.println("Total time elapsed: " + totalTime);
        System.out.println("Total population across simulation: " + population);
        System.out.println("Total healthy: " + healthy);
        System.out.println("Total Infected " + totalInfected);
        System.out.println("  Total cured: " + cured);
        System.out.println("  Total deceased: " + deceased);
        System.out.println("Percent healthy: " + getPercentHealthy());
        System.out.println("Total percent infected: " + getTotalPercentInfected());
        System.out.println("  Percent cured: " + getPercentCured());
        System.out.println("  Percent deceased: " + getPercentDeceased());
        System.out.println("****************************************************************");
    }

    public double getPercentHealthy() {
        return (double)healthy/population;
    }

    public double getTotalPercentInfected() {
        return (double)totalInfected/population;
    }

    public double getPercentCured() {
        return (double)cured/totalInfected;
    }

    public double getPercentDeceased() {
        return (double)deceased/totalInfected;
    }


    public long getTotalTime() {
        return totalTime;
    }

    public long getSimulationCount() {
        return simulationCount;
    }

    public long getPopulation() {
        return population;
    }

    public long getHealthy() {
        return healthy;
    }

    public long getTotalInfected() {
        return totalInfected;
    }

    public long getCured() {
        return cured;
    }

    public long getDeceased() {
        return deceased;
    }
}
