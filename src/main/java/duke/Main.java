package duke;

import duke.data.Duke;

/**
 * Main file to run the program.
 */
public class Main {

    /**
     * Main runner for the Duke bot.
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
