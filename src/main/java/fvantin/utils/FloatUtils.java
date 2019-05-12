package fvantin.utils;

public final class FloatUtils {

    private FloatUtils() {
        // Singleton
    }

    public static Float tryParseFloat(String toParse) {
        try {
            return Float.parseFloat(toParse);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
