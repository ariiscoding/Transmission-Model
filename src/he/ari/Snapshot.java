package he.ari;

public class Snapshot {
    //momentary collection of current status
    int time;
    int healthy;
    int infected;
    int hospitalized;
    int deceased;
    int cured;

    public Snapshot(Time time, Snapshot lastSnap) {
        this.time = time.getTime();
        healthy = 0;
        infected = lastSnap == null ? 0 : lastSnap.infected;
        deceased = 0;
        cured = 0;
        hospitalized = lastSnap == null ? 0 : lastSnap.getHospitalized();
    }

    public void infectedToDead() {
        //outside hospital
        infected--;
        deceased++;
    }

    public void infectedToHospital() {
        infected--;
        hospitalized++;
    }

    public void hospitalizedToHealthy() {
        hospitalized--;
        healthy++;
    }

    public void hospitalizedToDead() {
        hospitalized--;
        deceased++;
    }


    public void updateHospitalized (Hospital hospital) {hospitalized = hospital.people.size();}

    public void updateHealthy(int population) {
        healthy = population - infected - hospitalized - deceased - cured;
    }

    public void healthyToInfected() {
        infected++;
        healthy--;
    }

    public void updateDeceased (Graveyard graveyard) {
        deceased = graveyard == null ? 0 : graveyard.size();
    }

    public void updateCured (Heaven heaven) {
        cured = heaven == null ? 0 : heaven.size();
    }

    public int getHealthy() {
        return healthy;
    }

    public int getInfected() {
        return infected;
    }

    public int getDeceased() {
        return deceased;
    }

    public int getTime() {
        return time;
    }

    public int getCured() {
        return cured;
    }

    public int getHospitalized() {
        return hospitalized;
    }
}
