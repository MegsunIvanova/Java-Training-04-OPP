package christmasPastryShop.common;

public class DataValidator {

    public static void validateStringIsNotNullOrBlank(String text, String exceptionMessage) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void validateNumberIsNotLessOrEqualsToZero (double number, String exceptionMessage) {
        if (number <= 0) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
