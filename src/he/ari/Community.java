package he.ari;

public class Community extends Cluster {
    public Community(int size) {
        super();
        while (people.size() < size) {
            people.add(new Person(60));
        }
    }
}
