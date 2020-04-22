package he.ari;

import java.util.List;

public class Statistics {
    //designed to collect everything about the entire simulation
    //record data using Snapshots

    City city;
    List<Snapshot> history;
    boolean completeHistory; //if set to yes, history (list) only stores the most recent Snapshot
    Integer population;

    public Statistics(City city, boolean completeHistory) {
        this.city = city;
        this.completeHistory = completeHistory;
        this.population = getPopulation(city);
    }

    public static int getPopulation (City city) {
        int population = 0;
        for (Community cur : city.city) {
            population += cur.people.size();
        }
        return population;
    }
}
