package he.ari.model;

public class FinalSnapshot implements PrintableSnapshot {
    private int population;
    private int endTime;
    private int healthy;
    private int totalInfected;
    private int hospitalized;
    private int deceased;
    private int cured;

    public FinalSnapshot(int population, int endTime, int healthy, int infected, int hospitalized, int deceased, int cured) {
        this.population = population;
        this.endTime = endTime;
        this.healthy = healthy;
        this.totalInfected = deceased+cured;
        this.hospitalized = hospitalized;
        this.deceased = deceased;
        this.cured = cured;
    }

    public double getPercentHealthy() {
        return (double)healthy/population;
    }

    public double getTotalPercentInfected() {
        return (double)totalInfected/population;
    }

    public double getPercentDeceased() {
        return (double)deceased/totalInfected;
    }

    public double getPercentCured() {
        return (double)cured/totalInfected;
    }

    public int getPopulation() {
        return population;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getTime() {
        return endTime;
    }

    public int getHealthy() {
        return healthy;
    }

    public int getInfected() {
        return totalInfected;
    }

    public int getHospitalized() {
        return hospitalized;
    }

    public int getDeceased() {
        return deceased;
    }

    public int getCured() {
        return cured;
    }
}
