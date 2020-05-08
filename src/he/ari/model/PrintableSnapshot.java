package he.ari.model;

public interface PrintableSnapshot {
    public int getHealthy();
    public int getInfected();
    public int getHospitalized();
    public int getCured();
    public int getDeceased();
    public int getTime();
}
