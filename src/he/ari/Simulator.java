package he.ari;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Simulator {
    City city;
    Hospital hospital;
    Graveyard graveyard;
    Contagious contagion;
    private final Time time;
    Statistics stats;
    Heaven heaven;
    double intercommunityMobility;

    public void simulate() {
        while (!canEnd()) {
            iterate();
        }
    }

    public boolean canEnd() {
        //TODO rely on stats package to collect the number of infected people
        return true;
    }

    public void iterate() {
        //city has been initiated

        List<Person> nomads = new ArrayList<>();


        for (Community cur : city.city) {
            injectNomads(cur, nomads);
            extractNomads(cur, nomads);
            communitySpread(cur);
            deathProcessing(cur);
        }

        //post processing to place the rest of nomads
        extractNomads(city.city.get(0), nomads);

        //check hospital
        maintainHospital();

        //increment time
        time.inc();
    }

    private void initialInfection (int initialInfected) {
        while(initialInfected > 0) {
            for (Community cur :city.city) {
                for (Person p : cur.people) {
                    if (contagion.willInfect(p)) {
                        p.infect(time);
                        initialInfected--;
                    }
                }
            }
        }
    }

    private void injectNomads (Community community, List<Person> nomads) {
        while (!nomads.isEmpty()) {
            Person p = nomads.get(nomads.size()-1);
            nomads.remove(nomads.size()-1);
            community.people.add(p);
        }
    }

    private void extractNomads (Community community, List<Person> nomads) {
        int size = community.people.size();
        int count = 0;
        while (size > 0) {
            if (Utils.checkChance(intercommunityMobility)) count++;
            size--;
        }

        while (count > 0) {
            nomads.add(community.delete(community.size()-1));
            count--;
        }
    }

    private void communitySpread (Community community) {
        for (int i = 0; i < community.size(); i++) {
            for (int j = i+1; j < community.size(); j++) {
                if (contagion.willInfect(community.get(i), community.get(j))) {
                    community.get(i).infect(time);
                    community.get(j).infect(time);
                }
            }
        }
    }

    private void deathProcessing (Community community) {
        for (int i = 0; i < community.size(); i++) {
            if (contagion.willKill(community.get(i))) {
                community.kill(i, graveyard);
            }
        }
    }

    private void maintainHospital() {
        List<Integer> toBeReleased = new ArrayList<>();
        for (int i = 0; i < hospital.size(); i++) {
            if (hospital.canRelease(i, time)) toBeReleased.add(i);
        }
        for (int i : toBeReleased) hospital.cureFromHospital(i, heaven);

        List<Integer> toDie = new ArrayList<>();
        for(int i = 0; i < hospital.size(); i++) {
            if (hospital.willKillInHospital(i, contagion)) toDie.add(i);
        }
        for (int i : toDie) hospital.killInHospital(i, graveyard);
    }


    public Simulator(City city, Hospital hospital, Graveyard graveyard, Contagious contagion, Time time, Statistics stats, int initialInfected, Heaven heaven, double intercommunityMobility) {
        this.city = city;
        this.hospital = hospital;
        this.graveyard = graveyard;
        this.contagion = contagion;
        this.time = time;
        this.stats = stats;
        initialInfection(initialInfected);
        this.intercommunityMobility = intercommunityMobility;
    }

    public static class Builder {
        private int numberOfCommunities;
        private int peoplePerCommunities;
        private int hospitalCapacity;
        private Contagious contagion;
        private boolean completeHistory;
        private int initialInfected;
        private double intercommunityMobility;
        private double deathRateDecrement;
        private int cureTime;

        public Builder() {
            //default values
            numberOfCommunities = 100;
            peoplePerCommunities = 10;
            hospitalCapacity = numberOfCommunities * peoplePerCommunities /10;
            contagion = new Virus(0.8, 0.0001);
            completeHistory = false;
            initialInfected = 5;
            intercommunityMobility = 0.2;
            deathRateDecrement = 0.8;
            cureTime = 14;
        }

        public Simulator build() {
            if (numberOfCommunities <= 0) throw new InputMismatchException("The number of communities must be positive.");
            if (peoplePerCommunities <= 0) throw new InputMismatchException("The number of people per community must be positive.");
            if (hospitalCapacity < 0) throw new InputMismatchException("Hospital capacity must be non-negative.");
            if (contagion == null) throw new InputMismatchException("Please provide a contagion.");
            if (initialInfected < 0) throw new InputMismatchException("Initial infected count must be non-negative.");
            if (initialInfected > numberOfCommunities*peoplePerCommunities) throw new InputMismatchException("Initial infected count cannot be larger than total population.");
            if (intercommunityMobility < 0) throw new InputMismatchException("Intercommunity Mobility must be non-negative.");
            if (deathRateDecrement < 0 || deathRateDecrement > 1) throw new InputMismatchException("Death rate decrement for the hospital must be within 0 and 1.");
//            if (contagion instanceof Virus) {
//                Virus temp = (Virus) contagion;
//                if (temp.getDeathRate() - deathRateDecrement < 0) {
//                    throw new InputMismatchException("Death rate decrement cannot decrease virus death rate to negative.");
//                }
//            }
            if (cureTime < 0) throw new InputMismatchException("Cure time must be non-negative.");

            City city = new City(numberOfCommunities, peoplePerCommunities);
            Hospital hospital = new Hospital(hospitalCapacity, deathRateDecrement, cureTime);
            Graveyard graveyard = new Graveyard();
            Time time = new Time();
            Statistics stats = new Statistics(city, completeHistory);
            Heaven heaven = new Heaven();

            return new Simulator(city, hospital, graveyard, contagion, time, stats, initialInfected, heaven, intercommunityMobility);
        }

        public int getInitialInfected() {
            return initialInfected;
        }

        public void setInitialInfected(int initialInfected) {
            this.initialInfected = initialInfected;
        }

        public int getNumberOfCommunities() {
            return numberOfCommunities;
        }

        public void setNumberOfCommunities(int numberOfCommunities) {
            this.numberOfCommunities = numberOfCommunities;
        }

        public int getPeoplePerCommunities() {
            return peoplePerCommunities;
        }

        public void setPeoplePerCommunities(int peoplePerCommunities) {
            this.peoplePerCommunities = peoplePerCommunities;
        }

        public int getHospitalCapacity() {
            return hospitalCapacity;
        }

        public void setHospitalCapacity(int hospitalCapacity) {
            this.hospitalCapacity = hospitalCapacity;
        }

        public Contagious getContagion() {
            return contagion;
        }

        public void setContagion(Contagious contagion) {
            this.contagion = contagion;
        }

        public boolean isCompleteHistory() {
            return completeHistory;
        }

        public void setCompleteHistory(boolean completeHistory) {
            this.completeHistory = completeHistory;
        }

        public double getIntercommunityMobility() {
            return intercommunityMobility;
        }

        public void setIntercommunityMobility(double intercommunityMobility) {
            this.intercommunityMobility = intercommunityMobility;
        }

        public double getDeathRateDecrement() {
            return deathRateDecrement;
        }

        public void setDeathRateDecrement(double deathRateDecrement) {
            this.deathRateDecrement = deathRateDecrement;
        }

        public int getCureTime() {
            return cureTime;
        }

        public void setCureTime(int cureTime) {
            this.cureTime = cureTime;
        }
    }
}
