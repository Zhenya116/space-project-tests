package utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static int positiveInt(int minInclusive, int maxInclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }
}
