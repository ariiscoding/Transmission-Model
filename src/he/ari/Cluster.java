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

    public int size() {
        return people.size();
    }

    public Person get(int index) {
        return people.get(index);
    }

    public boolean kill (Person person, Graveyard graveyard) {
        if (person.getState() != STATE.TRANSMITTED) {
            return false;
        }
        graveyard.inc();
        people.remove(person);
        return true;
    }

    public boolean kill (int index, Graveyard graveyard) {
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
