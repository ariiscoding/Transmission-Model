package he.ari.model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    //designed to collect everything about the entire simulation
    //record data using Snapshots

    City city;
    List<Snapshot> history;
    boolean completeHistory; //if set to yes, history (list) only stores the most recent Snapshot
    public final int population;
    public FinalSnapshot finalSnapshot;

    public Statistics(City city, boolean completeHistory) {
        history = new ArrayList<>();
        this.city = city;
        this.completeHistory = completeHistory;
        this.population = getPopulation(city);
    }

    public void initialReport() {
        System.out.println("Total population: "+ population);
        if (!history.isEmpty()) System.out.println("Initial infection count: " + history.get(history.size()-1).getInfected());
        System.out.println("Simulation now starts!");
        System.out.println("********************************");
        System.out.println("");
    }

    public void reportCurrentSnapshot() {
        if (history.isEmpty()) {
            System.out.println("No current stats available.");
        }
        Snapshot snap = getLastSnapshot();
        System.out.println("---------------------------------");
        System.out.println("Time: " + snap.getTime());
        printStats(snap);
        System.out.println("---------------------------------");
        System.out.println("");
    }

    public void finalReport() {
        if (finalSnapshot==null) generateFinalSnapshot();
        System.out.println("********************************");
        System.out.println("Simulation has finished!");
        System.out.println("Total time elapsed: " + finalSnapshot.getEndTime());
        printStats(finalSnapshot);
        System.out.println("Percent healthy: " + finalSnapshot.getPercentHealthy());
        System.out.println("Total percent infected: " + finalSnapshot.getTotalPercentInfected());
        System.out.println("  Percent cured: " + finalSnapshot.getPercentCured());
        System.out.println("  Percent deceased: " + finalSnapshot.getPercentDeceased());
    }

    private void printStats(PrintableSnapshot snap) {
        System.out.println("Healthy: " + snap.getHealthy());
        System.out.println("Infected: " + snap.getInfected());
        System.out.println("Hospitalized : " + snap.getHospitalized());
        System.out.println("Cured: " + snap.getCured());
        System.out.println("Deceased: " + snap.getDeceased());
    }

    public Snapshot getLastSnapshot() {
        if (history.isEmpty()) return null;
        return history.get(history.size()-1);
    }

    public void updateSnapshot(Snapshot snapshot, Heaven heaven, Graveyard graveyard, Hospital hospital, City city) {
        snapshot.enumerate(city);
        snapshot.updateHospitalized(hospital);
        snapshot.updateCured(heaven);
        snapshot.updateDeceased(graveyard);
    }

    public static int getPopulation (City city) {
        int population = 0;
        for (Community cur : city.city) {
            population += cur.people.size();
        }
        return population;
    }

    public Snapshot generate(Time time, Heaven heaven, Graveyard graveyard, Hospital hospital, City city) {
        Snapshot snapshot = new Snapshot(time, city, hospital, graveyard, heaven);
        if (!completeHistory) {
            while(!history.isEmpty()) history.remove(history.size()-1);
        }
        history.add(snapshot);

        return snapshot;
    }

    public FinalSnapshot generateFinalSnapshot() {
        Snapshot snap = getLastSnapshot();
        FinalSnapshot finalSnapshot = new FinalSnapshot(population, snap.getTime(),snap.getHealthy(), snap.getHospitalized(),snap.getDeceased(),snap.getCured());
        this.finalSnapshot = finalSnapshot;
        return finalSnapshot;
    }

    public Integer getPopulation() {
        return population;
    }

    public int currentInfected() {
        Snapshot cur = getLastSnapshot();
        return cur == null ? 0 : cur.getInfected();
    }

    public int currentHospitalized() {
        Snapshot cur = getLastSnapshot();
        return cur == null ? 0 : cur.getHospitalized();
    }
}
