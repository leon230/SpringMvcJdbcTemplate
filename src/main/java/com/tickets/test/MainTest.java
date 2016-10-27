package com.tickets.test;

import com.tickets.controller.TicketController;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Leon on 2016-10-26.
 */
public class MainTest {
    public static void main(String[] args) {
        TestSuite suite = new TestSuite(JavaTest.class, PermissionTest.class, TicketController.class);
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("Number of test cases = " + result.runCount());
/**
 * Test JavaTest class
 */
    Result resultJavaTest = JUnitCore.runClasses(JavaTest.class);

    for (Failure failure : resultJavaTest.getFailures()) {
        System.out.println(failure.toString());
    }

    System.out.println(resultJavaTest.wasSuccessful());
/**
 * Test TicketControllerTest
 */


    }
}
