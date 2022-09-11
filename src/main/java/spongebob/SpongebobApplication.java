package spongebob;

import spongebob.command.ICommand;

/**
 * Represents the main class of the spongebob program.
 */
public class SpongebobApplication {
    private Storage storage;
    private TaskList taskList;

    /**
     * Returns an instance of spongebob.
     *
     */
    public SpongebobApplication() {
        this.storage = new Storage();
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
     * Runs main method for program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpongebobApplication spongebob = new SpongebobApplication();
    }
}
