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
    public void setTerminated() {
        this.isTerminated = true;
    }

    /**
     * Returns response from the input string by parsing the string and executing the corresponding command.
     *
     * @param input String to be parsed into command and to be executed
     * @return a String to be displayed back to user
     */
    public String getResponse(String input) {
        if (isTerminated) {
            return Ui.endedSessionPrint();
        } else {
            return Parser.parseCommand(input, this.taskList, this.storage, this);
        }
    }
}
