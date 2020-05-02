package he.ari;

public class Graveyard extends Cluster {
    private int deceased;

    public Graveyard() {
        deceased = 0;
    }

    public void inc() {
        deceased++;
    }

    @Override
    public int size() {
        return deceased;
    }
}
