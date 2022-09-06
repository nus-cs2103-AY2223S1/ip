package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    private static void testAssertions() {
        int i = 0;
        boolean isAssertionsEnabled = false;

        try {
            assert i > 0;
        } catch (AssertionError e) {
            isAssertionsEnabled = true;
            System.out.println("Assertions are enabled");
        }

        if (!isAssertionsEnabled) {
            throw new RuntimeException("Please enable assertions by specifying with command line flag -ea");
        }
    }

    public static void main(String[] args) {
        testAssertions();
        Application.launch(Main.class, args);
    }
}
