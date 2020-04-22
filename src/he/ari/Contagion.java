package he.ari;

public abstract class Contagion implements Contagious{
    private double transmissionRate;

    public Contagion(double transmissionRate) {
        this.transmissionRate = transmissionRate;
    }

    public double getTransmissionRate() {
        return transmissionRate;
    }

    @Override
    public boolean willInfect(Person p1, Person p2) {
        if (p1.getState() == STATE.TRANSMITTED && p2.getState() == STATE.TRANSMITTED) {
            return false;
        }
        else if (p1.getState() != STATE.TRANSMITTED && p2.getState() != STATE.TRANSMITTED) {
            return false;
        }

        return Utils.checkChance(transmissionRate);
    }

    @Override
    public boolean willInfect(Person p) {
        if (p.getState() != STATE.HEALTHY) {
            return false;
        }
        return Utils.checkChance(transmissionRate);
    }

    @Override
    public boolean willKill(Person person) {
        return false;
    }
}
