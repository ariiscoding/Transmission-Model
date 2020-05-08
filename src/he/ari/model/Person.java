package he.ari.model;

public class Person {
    STATE state;
    int age;
    private Integer infectionTime;

    public Person (int upperAge) {
        state = STATE.HEALTHY;
        age = randomAge(upperAge);
        infectionTime = null;
    }

    public boolean infect(Time time) {
        if (state != STATE.HEALTHY) {
            return false;
        }
        state = STATE.TRANSMITTED;
        infectionTime = time.getTime();
        return true;
    }

    private int randomAge(int upper) {
        return Utils.randomInt(0, upper);
    }

    public int getAge() {
        return age;
    }

    public STATE getState() {
        return state;
    }

    public Integer getInfectionTime() {
        return infectionTime;
    }
}
