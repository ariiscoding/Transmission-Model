package he.ari.model;

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
}
