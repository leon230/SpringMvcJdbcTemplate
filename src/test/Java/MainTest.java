import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Leon on 2016-10-26.
 */
public class MainTest {
    public static void main(String[] args) {
//        TestSuite suite = new TestSuite(JavaTest.class, TicketController.class);
//        TestResult result = new TestResult();
//        suite.run(result);
//        System.out.println("Number of test cases = " + result.runCount());
/**
 * Test Ticket class
 */
    Result resultTicketTest = JUnitCore.runClasses(TicketTest.class);

    for (Failure failure : resultTicketTest.getFailures()) {
        System.out.println(failure.toString());
    }

    System.out.println(resultTicketTest.wasSuccessful());

    }
}
