package duke;

import command.Command;

/**
 * The main class that runs the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Duke {

    private final Storage STORAGE;
    private final TaskList TASKLIST;
    private final Ui UI;
    private final Parser PARSER;

    /**
     * Initializes a Duke object with a file path to load and store tasks.
     */

    public Duke() {
        TaskList temp;
        UI = new Ui();
        String filePath = "data/duke.txt";
        STORAGE = new Storage(filePath);
        try {
            temp = new TaskList(STORAGE.loadLocalData());
        } catch (DukeException e) {
            UI.showError(e.getMessage());
            temp = new TaskList();
        }
        TASKLIST = temp;
        PARSER = new Parser();
    }

    /**
     * Runs the Duke program using the CLI instead of GUI.
     */

    public void run() {
        UI.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = UI.readCommand();
                Command command = PARSER.parse(input);
                String response = command.execute(TASKLIST, UI, STORAGE);
                isExit = command.isExit();
                assert !response.isEmpty();
                UI.print(response);
            } catch (DukeException e) {
                UI.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns the appropriate response to the user input in the GUI.
     *
     * @return the appropriate response to the user input as a String
     */

    public String getResponse(String input) {
        try {
            Command command = PARSER.parse(input);
            String response = command.execute(TASKLIST, UI, STORAGE);
            assert !response.isEmpty();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

