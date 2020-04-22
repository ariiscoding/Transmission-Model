package he.ari;

public class Virus extends Contagion {
    private double deathRate;

    public Virus(double transmissionRate, double deathRate) {
        super(transmissionRate);
        this.deathRate = deathRate;
    }

    public double getDeathRate() {
        return deathRate;
    }

    @Override
    public boolean willKill(Person person) {
        if (person.getState() != STATE.TRANSMITTED) {
            return false;
        }
        return Utils.checkChance(deathRate);
    }
}
