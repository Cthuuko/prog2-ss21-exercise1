import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 25;

    /**
     * Checks the password if it meets the requirement given by the task
     *
     * @param password The password to validate
     * @return True if password is valid - False otherwise
     */
    public static boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }

        return checkLength(password) && checkLowerCase(password) && checkCapitalCase(password) &&
                checkNumbers(password) && checkSpecialSymbols(password) &&
                checkProgressiveAndConsecutiveNumbers(password);
    }

    /**
     * Checks if the password is within the boundary
     *
     * @param password The password to validate
     * @return True if password passes the validation - False otherwise
     */
    public static boolean checkLength(String password) {
        return password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH;
    }

    /**
     * Checks if the password contains at least one small letter
     *
     * @param password The password to validate
     * @return True if password passes the validation - False otherwise
     */
    public static boolean checkLowerCase(String password) {
        return getMatcher(password, "[a-z]+").find(0);
    }

    /**
     * Checks if the password contains at least one capital letter
     *
     * @param password The password to validate
     * @return True if password passes the validation - False otherwise
     */
    public static boolean checkCapitalCase(String password) {
        return getMatcher(password, "[A-Z]+").find(0);
    }

    /**
     * Checks if the password contains at least one number
     *
     * @param password The password to validate
     * @return True if password passes the validation - False otherwise
     */
    public static boolean checkNumbers(String password) {
        return getMatcher(password, "\\d+").find(0);
    }

    /**
     * Checks if the password contains at least one of the following symbols: ()#$?!%/@
     * and doesn't contain any other special symbol
     *
     * @param password The password to validate
     * @return True if password passes the validation - False otherwise
     */
    public static boolean checkSpecialSymbols(String password) {
        return getMatcher(password, "[()#$?!%/@]+").find(0) && password.replaceAll("[A-Za-z\\d()#$?!%/@]", "").length() == 0;
    }

    /**
     * Checks if the password doesn't contain a sequence of progressive numbers or consecutive sequence of the same number
     *
     * @param password The password to validate
     * @return True if password passes the validation - False otherwise
     */
    public static boolean checkProgressiveAndConsecutiveNumbers(String password) {
        Matcher m = getMatcher(password, "\\d+");
        if (m.find(0)) {
            char[] match = m.group().toCharArray();
            StringBuilder consecutiveNumValidator = new StringBuilder();
            if (match.length > 2) {
                for (int i = 0; i < match.length - 1; i++) {
                    consecutiveNumValidator.append(Character.getNumericValue(match[i + 1]) - Character.getNumericValue(match[i]));
                }

                return !Pattern.compile("1{2,}").matcher(consecutiveNumValidator.toString()).find(0) &&
                        !Pattern.compile("0{3,}").matcher(consecutiveNumValidator.toString()).find(0);
            }
        }
        return true;
    }

    /**
     * Prepares the Regex Matcher for given regex and string to check
     *
     * @param password The password to check
     * @param regex    The regex to be applied
     * @return The Matcher object to find matches for given regex.
     */
    private static Matcher getMatcher(String password, String regex) {
        return Pattern.compile(regex).matcher(password);
    }
}
