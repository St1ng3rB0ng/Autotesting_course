import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import student.anatoliy.PasswordPolicy;
import org.testng.annotations.BeforeMethod;
import student.anatoliy.exceptions.IllegalPasswordException;

public class PasswordPolicyTest {
    @BeforeMethod
    public void setUp() {
        System.out.println("\ntest started:");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("test ended");
    }

    @Test(priority = 1)
    public void smokeTest() {
        System.out.println("Trying to initialize the class: PasswordPolicy");
        PasswordPolicy policy = new PasswordPolicy();
        Assert.assertNotNull(policy, "Policy should be successfully initialized");
    }

    @Test(priority = 2)
    public void testValidPassword() {
        String legalPassword = "l3galPASS";
        System.out.println("Trying to use password: [" + legalPassword + "]");
        Assert.assertTrue(PasswordPolicy.checksIfValid(legalPassword));
    }

    @Test(priority = 2)
    public void boundaryTest() {
        String boundaryPassword = "8Symbols";
        System.out.println("Trying to use password: [" + boundaryPassword + "]");
        Assert.assertTrue(PasswordPolicy.checksIfValid(boundaryPassword));
    }

    @Test(expectedExceptions = IllegalPasswordException.class, priority = 3)
    public void testInValidPasswordContainsSpaces() {
        String illegalPassword = " I l l e g a l ";
        System.out.println("Trying to use password: [" + illegalPassword + "]");
        PasswordPolicy.checksIfValid(illegalPassword);
    }

    @Test(expectedExceptions = IllegalPasswordException.class, priority = 3)
    public void testInValidPasswordTooShort() {
        String illegalPassword = "Illegal"; //7 symbols < 8 symbols required
        System.out.println("Trying to use password: [" + illegalPassword + "]");
        PasswordPolicy.checksIfValid(illegalPassword);
    }

}