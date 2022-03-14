import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class and Test-class written by Miguel Arcilla
 * https://github.com/Cthuuko/prog2-ss21-exercise1
 */
public class PasswordValidatorTest {

    /**
     * Testcase for a password that meets all requirements
     */
    @Test
    public void checkPassword() {
        String password = "ThisPassShouldWork124())";
        assertTrue(PasswordValidator.checkPassword(password));
    }

    /**
     * Testcase for a password that doesn't meet one of the requirements: Password should be between 8 and 25 characters
     */
    @Test
    public void checkPasswordLengthError() {
        String password = "aB1!";
        assertFalse(PasswordValidator.checkPassword(password));

        password = "Abcdeabcdeabcdeabcdeabcd1!";
        assertFalse(PasswordValidator.checkPassword(password));
    }

    /**
     * Testcase for a password that doesn't meet one of the requirements: At least one capital and lower case letter
     */
    @Test
    public void checkPasswordCaseError() {
        String password = "thispasswordshouldwork124())";
        assertFalse(PasswordValidator.checkPassword(password));

        password = "THISPASSWORDSHOULDWORK124())";
        assertFalse(PasswordValidator.checkPassword(password));
    }

    /**
     * Testcase for a password that doesn't meet one of the requirements: At least one number
     */
    @Test
    public void checkPasswordNumbersMissingError() {
        String password = "ThisPassShouldWorkkkk())";
        assertFalse(PasswordValidator.checkPassword(password));
    }

    /**
     * Testcase for a password that doesn't meet one of the requirements: At least one special character from ()#$?!%/@
     */
    @Test
    public void checkPasswordSpecialCharMissingError() {
        String password = "ThisPassShouldWork124fff";
        assertFalse(PasswordValidator.checkPassword(password));
    }

    /**
     * Testcase for a password that doesn't meet one of the requirements: No special characters allowed other than ()#$?!%/@
     */
    @Test
    public void checkPasswordInvalidSpecialCharError() {
        String password = "ThisPassShouldWork124()§";
        assertFalse(PasswordValidator.checkPassword(password));
    }

    // Extensions

    /**
     * Testcase for a password that doesn't meet one of the requirements: No more than 2 progressive numbers (123) and
     * No more than 3 same consecutive numbers (1111)
     */
    @Test
    public void checkPasswordProgressiveAndConsecutiveNumbersError() {
        String password = "ThisPassShouldWork123()";
        assertFalse(PasswordValidator.checkPassword(password));

        password = "ThisPassShouldWork1111()";
        assertFalse(PasswordValidator.checkPassword(password));
    }
}
