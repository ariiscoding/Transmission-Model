package he.ari;

public class Graveyard extends Cluster {
    private int deceased;

    public Graveyard() {
        deceased = 0;
    }

    public void inc() {
        deceased++;
    }

    public int getDeceased() {
        return deceased;
    }
}
