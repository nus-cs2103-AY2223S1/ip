package duke;

/**
 * duke.Main file that contains the flow of the program
 */
public class Duke {
    private static final String filePath = "data/duke.txt";
    private boolean isTerminated;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs an instance of Duke that will initialize the set-ups
     */
    public Duke() {
        this.isTerminated = false;
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        storage.readResult(taskList);
    }

    /**
     * Sets the isTerminated to be true
     */
    protected void setTerminated() {
        this.isTerminated = true;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (isTerminated) {
            return Ui.endedSessionPrint();
        } else {
            return Parser.parseCommand(input, this.taskList, this.storage, this);
        }
    }
}
