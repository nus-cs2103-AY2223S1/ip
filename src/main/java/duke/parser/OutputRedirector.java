package duke.parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A helper class redirects output from print to a concatenated strong.
 */
public class OutputRedirector {
    private static boolean isCaptured;
    private static ByteArrayOutputStream newStream;
    private static PrintStream previousStream;


    /**
     * Starts recording standard output
     */
    public static void start() {
        if (isCaptured) {
            return;
        }
        isCaptured = true;
        // create a separate stream
        newStream = new ByteArrayOutputStream();
        PrintStream currStream = new PrintStream(newStream);
        // create a space to store previous output stream
        previousStream = System.out;
        // switch to the new stream created
        System.setOut(currStream);
    }

    /**
     * Stops recording standard output
     */
    public static String stop() {
        if (!isCaptured) {
            return "";
        }
        // switch back to previous stream
        System.setOut(previousStream);
        String capturedString = newStream.toString();

        newStream = null;
        previousStream = null;
        isCaptured = false;

        return capturedString;
    }

    public static boolean isCaptured() {
        return isCaptured;
    }
}
