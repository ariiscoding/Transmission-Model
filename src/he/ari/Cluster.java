package he.ari;

import java.util.ArrayList;
import java.util.List;

public abstract class Cluster {
    List<Person> people;

    Cluster() {
        people = new ArrayList<>();
    }

    public Person delete(int index) {
        Person p = people.get(index);
        people.remove(index);
        return p;
    }

    public abstract int size();

    public Person get(int index) {
        return people.get(index);
    }

    public boolean kill (int index, Graveyard graveyard) {
        //stats update has to be handled by the user
        if(get(index).getState() != STATE.TRANSMITTED) return false;

        graveyard.inc();
        people.remove(index);
        return true;
    }

    public boolean cure (int index, Heaven heaven) {
        if (get(index).getState() != STATE.TRANSMITTED) return false;

        heaven.inc();
        people.remove(index);
        return true;
    }
}
