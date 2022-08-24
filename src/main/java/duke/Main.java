package duke;

import java.io.IOException;

/**
 * Main class to run and initialize the Duke class.
 * @author Jason
 */
public class Main {
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.initialize();
    }
}
