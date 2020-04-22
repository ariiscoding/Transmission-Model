package he.ari;

import java.util.ArrayList;
import java.util.List;

public class City {
    List<Community> city;

    public City(int numberOfClusters, int sizeOfCluster) {
        city = new ArrayList<>();
        while (city.size() < numberOfClusters) {
            city.add(new Community(sizeOfCluster));
        }
    }
}
