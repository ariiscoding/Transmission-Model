package he.ari.concurrency;

import he.ari.model.FinalSnapshot;
import he.ari.tools.Utils;
import jdk.jshell.execution.Util;

import java.util.List;
import java.util.Map;

public class ThreadStats {
    private long simulationCount;
    private long timedOutThreadCount;
    private long totalTime;
    private long population;
    private long healthy;
    private long totalInfected;
    private long cured;
    private long deceased;

    public ThreadStats (List<FinalSnapshot> results, int timedOutThreadCount) {
        this.simulationCount = results.size();
        this.timedOutThreadCount = timedOutThreadCount;
        totalTime = 0;
        this.population = 0;
        this.healthy = 0;
        this.totalInfected = 0;
        this.cured = 0;
        this.deceased = 0;

        for (FinalSnapshot cur : results) {
            population += cur.getPopulation();
            totalTime += cur.getEndTime();
            healthy += cur.getHealthy();
            totalInfected += cur.getInfected();
            cured += cur.getCured();
            deceased += cur.getDeceased();
        }
    }

    @Deprecated
    public ThreadStats (Map<Integer, FinalSnapshot> database) {
        this.simulationCount = database.size();
        this.timedOutThreadCount = 0;
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
        System.out.println("Total number of simulations requested: " + simulationCount);
        System.out.println("Number of simulations timed out: " + timedOutThreadCount);
        System.out.println("Number of simulations succeeded: " + (simulationCount-timedOutThreadCount));
        System.out.println("Total time elapsed: " + totalTime);
        System.out.println("Total population across simulation: " + population);
        System.out.println("Total healthy: " + healthy);
        System.out.println("Total Infected " + totalInfected);
        System.out.println("  Total cured: " + cured);
        System.out.println("  Total deceased: " + deceased);
        System.out.println("Percent healthy: " + showPercentHealthy());
        System.out.println("Total percent infected: " + showTotalPercentInfected());
        System.out.println("  Percent cured: " + showPercentCured());
        System.out.println("  Percent deceased: " + showPercentDeceased());
        System.out.println("****************************************************************");
    }

    public double getPercentHealthy() {
        return (double)healthy/population;
    }

    public String showPercentHealthy() {
        return Utils.toPercentage(getPercentHealthy());
    }

    public double getTotalPercentInfected() {
        return (double)totalInfected/population;
    }

    public String showTotalPercentInfected() {
        return Utils.toPercentage(getTotalPercentInfected());
    }

    public double getPercentCured() {
        return (double)cured/totalInfected;
    }

    public String showPercentCured() {
        return Utils.toPercentage(getPercentCured());
    }

    public double getPercentDeceased() {
        return (double)deceased/totalInfected;
    }

    public String showPercentDeceased() {
        return Utils.toPercentage(getPercentDeceased());
    }

    public long getTimedOutThreadCount() {
        return timedOutThreadCount;
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
