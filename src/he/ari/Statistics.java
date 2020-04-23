package he.ari;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    //designed to collect everything about the entire simulation
    //record data using Snapshots

    City city;
    List<Snapshot> history;
    boolean completeHistory; //if set to yes, history (list) only stores the most recent Snapshot
    public final int population;

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
        Snapshot snap = history.get(history.size()-1);
        System.out.println("---------------------------------");
        System.out.println("Time: " + snap.getTime());
        System.out.println("Healthy: " + snap.getHealthy());
        System.out.println("Infected: " + snap.getInfected());
        System.out.println("Cured: " + snap.getCured());
        System.out.println("Deceased: " + snap.getDeceased());
        System.out.println("---------------------------------");
        System.out.println("");
    }

    public Snapshot getLastSnapshot() {
        if (history.isEmpty()) return null;
        return history.get(history.size()-1);
    }

    public void updateSnapshot(Snapshot snapshot, Heaven heaven, Graveyard graveyard) {
        snapshot.updateCured(heaven);
        snapshot.updateDeceased(graveyard);
        snapshot.updateHealthy(population);
    }

    public static int getPopulation (City city) {
        int population = 0;
        for (Community cur : city.city) {
            population += cur.people.size();
        }
        return population;
    }

    public Snapshot generate(Time time) {
        Snapshot snapshot = new Snapshot(time, getLastSnapshot());
        if (!completeHistory) {
            while(!history.isEmpty()) history.remove(history.size()-1);
        }
        history.add(snapshot);

        return snapshot;
    }

    public Integer getPopulation() {
        return population;
    }

    public int currentInfected() {
        Snapshot cur = getLastSnapshot();
        return cur == null ? -1 : cur.getInfected();
    }
}
