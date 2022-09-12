package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * ConsoleRecorder is a class that can record the system.out.println lines in the console and return them as a string
 */
public class ConsoleRecorder {
    private ByteArrayOutputStream baos;
    private PrintStream pStream;
    private PrintStream old;
    private boolean isStarted;

    /**
     * Constructor for ConsoleRecorder.
     */
    public ConsoleRecorder() {
        baos = new ByteArrayOutputStream();
        pStream = new PrintStream(baos);
        old = System.out;
    }

    /**
     * Starts the recording of the console outputs. Also keeps a record of the old console.
     */
    public void start() {
        isStarted = true;
        baos = new ByteArrayOutputStream();
        pStream = new PrintStream(baos);
        old = System.out;
        System.setOut(pStream);
    }


    /**
     * @return a String of all the outputs in the console when the start() command was called till this method was
     * called.
     * @throws DukeException when the start() method hasn't been called
     */
    public String stopAndReturn() throws DukeException {
        if (!isStarted) {
            throw new DukeException("console recorder's start() has not been called!");
        }
        isStarted = false;
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

}
