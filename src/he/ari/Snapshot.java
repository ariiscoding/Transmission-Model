package he.ari;

import org.jetbrains.annotations.NotNull;

public class Snapshot {
    //momentary collection of current status
    int time;
    int healthy;
    int infected;
    int hospitalized;
    int deceased;
    int cured;

    public Snapshot(Time time, City city, Hospital hospital, Graveyard graveyard, Heaven heaven) {
        this.time = time.getTime();
        updateHospitalized(hospital);
        updateDeceased(graveyard);
        updateCured(heaven);
        enumerate(city);
    }

    public void enumerate(City city) {
        for (Community com : city.city) {
            for (Person p : com.people) {
                if (p.state == STATE.HEALTHY) healthy++;
                else if (p.state == STATE.TRANSMITTED) infected++;
            }
        }
    }

    public void updateHospitalized(Hospital hospital) {
        hospitalized = hospital.size();
    }

    public void updateDeceased(@NotNull Graveyard graveyard) {
        deceased = graveyard.size();
    }

    public void updateCured (Heaven heaven) {
        cured = heaven.size();
    }

    public int getTime() {
        return time;
    }

    public int getHealthy() {
        return healthy;
    }

    public int getInfected() {
        return infected;
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
