package spongebob;

import spongebob.command.ICommand;

/**
 * Represents the main class of the Duke program.
 */
public class SpongebobApplication {
    private Storage storage;
    private TaskList taskList;

    /**
     * Returns an instance of Duke.
     *
     * @param filePath String location of duke.txt.
     */
    public SpongebobApplication(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Returns response from command execution.
     *
     * @param input String input from input text field.
     * @return String response from the command execution.
     */
    public String process(String input) {
        ICommand cmd = Parser.parse(input);
        return cmd.execute(storage, taskList);
    }

    /**
     * Main method for Duke program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpongebobApplication spongebob = new SpongebobApplication("data/spongebob.txt");
    }
}
