package he.ari.tools;

import he.ari.model.Simulator;

import java.util.Random;

public class Utils {
    private static Random random = new Random();

    public static int randomInt (int lower, int upper) {
        return lower + random.nextInt(upper - lower);
    }

    public static double randomDouble() {
        return random.nextDouble();
    }

    public static boolean checkChance(double rate) {
        return randomDouble() < rate;
    }

    public static String toPercentage (double n, int decimals) {
        return String.format("%."+decimals+"f", n*100)+"%";
    }

    public static String toPercentage (double n) {
        /*
        with 2 decimals, by default
         */
        return toPercentage(n, 2);
    }

    public static int calcHospitalCapacity (Simulator.Builder builder, double percent) {
        int communities = builder.getNumberOfCommunities();
        int populationPerCommunities = builder.getPeoplePerCommunities();

        return (int)(communities * populationPerCommunities * percent);
    }
}
