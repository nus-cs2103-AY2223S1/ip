package duke;

import duke.command.ICommand;

/**
 * Represents the main class of the Duke program.
 */
public class DukeApplication {
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Returns an instance of Duke.
     * @param filePath String location of duke.txt.
     */
    public DukeApplication(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Returns response from command execution.
     * @param input String input from input text field.
     * @return String response from the command execution.
     */
    public String process(String input) {
        ICommand cmd = Parser.parse(input);
        return cmd.execute(storage, taskList);
    }

    /**
     * Main method for Duke program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        DukeApplication dk = new DukeApplication("data/duke.txt");
    }
}
