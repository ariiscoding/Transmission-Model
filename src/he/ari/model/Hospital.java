package he.ari.model;

import he.ari.tools.Utils;

public class Hospital extends Cluster {
    public final int capacity;
    double deathRateDecrement;
    private int cureTime;

    public Hospital(int capacity, double deathRateDecrement, int cureTime) {
        super();
        this.capacity = capacity;
        this.deathRateDecrement = deathRateDecrement;
        this.cureTime = cureTime;
    }

    public int getCapacity() {
        return capacity;
    }

    private boolean release (int index) {
        if (index < 0 || index >= people.size()) return false;

        people.remove(index);
        return true;
    }

    public boolean admit (Person person) {
        if (person.getState() != STATE.TRANSMITTED) return false;

        if (people.size() >= capacity) return false;

        people.add(person);
        return true;
    }

    public boolean cureFromHospital (int index, Heaven heaven) {
        if (!release(index)) return false;
        heaven.inc();
        return true;
    }

    public boolean killInHospital (int index, Graveyard graveyard) {
        if (!release(index)) return false;
        graveyard.inc();
        return true;
    }

    public boolean canRelease (int index, Time time) {
        if (index < 0 || index >= people.size()) return false;

        return people.get(index).getInfectionTime() != null && time.getTime() - people.get(index).getInfectionTime() >= cureTime;
    }

    public boolean willKillInHospital (int index, Contagious contagion) {
        if (!(contagion instanceof Virus)) {
            return false;
        }
        Virus virus = (Virus) contagion;

        if (index < 0 || index >= people.size()) return false;

        return Utils.checkChance(virus.getDeathRate()*(1-deathRateDecrement));
    }

    @Override
    public int size() {
        return people.size();
    }
}
