package he.ari.model;

public class Heaven extends Cluster {
    //people who have been cured
    private int cured;

    public Heaven() {
        this.cured = 0;
    }

    public void inc() {
        cured++;
    }

    @Override
    public int size() {
        return cured;
    }
}
