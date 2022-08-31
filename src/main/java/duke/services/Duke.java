package duke.services;

import java.io.IOException;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    /**
     * Activates all of Duke's functionalities until stopped, and records saved data
     * @throws IOException From issues in loading and saving data
     */
    public static void run() throws IOException {
        Storage.loadData();
        UI.introduceSelf();
        Parser.handleUserInputs();
        Storage.saveData();
        UI.sayGoodbye();
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}
