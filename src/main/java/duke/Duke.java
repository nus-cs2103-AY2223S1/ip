package duke;

/**
 * duke.Main file that contains the flow of the program
 */
public class Duke {
    private static final String filePath = "data/duke.txt";
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs an instance of Duke that will initialize the set-ups
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        storage.readResult(taskList);

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return Parser.parseCommand(input, this.taskList, this.storage);
    }
}
