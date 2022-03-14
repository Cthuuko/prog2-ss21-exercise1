import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        if (password == null || password.length() <= MIN_LENGTH || password.length() >= MAX_LENGTH) {
            return false;
        }

        Set<String> regexPatterns = new HashSet<>(List.of("[A-Z]+", "[a-z]+", "\\d+", "[()#$?!%/@]+"));

        for (String regex : regexPatterns) {
            if (!isValidPattern(password, regex)) {
                return false;
            }
        }

        return password.replaceAll("[A-Za-z\\d()#$?!%/@]", "").length() == 0;
    }

    /**
     * Matches the given regex with the given password,
     * if the given regex is matching digits, it will also check for progressive as well as consecutive numbers
     *
     * @param password The password to
     * @param regex    The regex to match to
     * @return True if the regex pattern has been found or for digits:
     * the numbers are not progressive (more than twice, e.g. 123) nor same consecutive (more than thrice, e.g. 1111) - False otherwise
     */
    private static boolean isValidPattern(String password, String regex) {
        Matcher m = Pattern.compile(regex).matcher(password);
        boolean patternFound = m.find(0);

        // Check for the extension requirements: progressive (123) and consecutive (111) numbers
        if (regex.equals("\\d+") && patternFound) {
            char[] match = m.group().toCharArray();
            StringBuilder consecutiveNumValidator = new StringBuilder();
            if (match.length > 2) {
                for (int i = 0; i < match.length - 1; i++) {
                    consecutiveNumValidator.append(Character.getNumericValue(match[i + 1]) - Character.getNumericValue(match[i]));
                }

                /*
                 Gets the difference between the numbers,
                 if there are more than 2 ones or 3 zeroes then one of the extension requirements has not been met.
                 */
                return !Pattern.compile("1{2,}").matcher(consecutiveNumValidator.toString()).find(0) &&
                        !Pattern.compile("0{3,}").matcher(consecutiveNumValidator.toString()).find(0);
            }
        }
        return patternFound;
    }
}
