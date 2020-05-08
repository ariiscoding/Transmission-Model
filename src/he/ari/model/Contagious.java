package he.ari.model;

public interface Contagious {
    public boolean willInfect (Person p1, Person p2);

    public boolean willKill(Person person);

    public boolean willInfect (Person p);

    public double getTransmissionRate();
}
