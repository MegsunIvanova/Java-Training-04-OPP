package glacialExpedition.common;

public class DataValidator {

    public static void ensureNotNullOrEmptyString(String string, String exceptionMessage) {
        if (string == null || string.trim().isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }
    }

    public static void ensureNotNegativeNumber(double number, String exceptionMessage) {
        if (number < 0) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
