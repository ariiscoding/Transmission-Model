package he.ari;

public class Heaven extends Cluster {
    //people who have been cured
    private int cured;

    public Heaven() {
        this.cured = 0;
    }

    public void inc() {
        cured++;
    }

    public int getCured() {
        return cured;
    }

}
