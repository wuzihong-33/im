package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IDUtils {
    private static AtomicInteger idBuilder = new AtomicInteger(0);
    public static String getId() {
        return String.valueOf(idBuilder.incrementAndGet());
    }
}
