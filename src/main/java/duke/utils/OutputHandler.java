package duke.utils;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Class to handle the calling of executing the command and reading
 * the System.out output.
 */
public class OutputHandler {


    /**
     * Executes the given command and reads the printed output.
     * @param action Command to be executed.
     * @return String representing the printed output.
     */
    public String getOutput(Command action) throws DukeException {
        // Solution below adapted from
        // https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        action.executeCommand();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }
}
