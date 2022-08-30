package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

/**
 * Represents all different types of command
 */
public abstract class Command {
    private String input;

    /**
     * Instantiates a new command
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Returns input of the command
     * @return Returns String input of the command
     */
    public String getInput() {
        return input;
    }

    /**
     * Executes the command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if bye command is executed to
     * end the program
     * @return Returns true if bye command is executed
     */
    public boolean isExit() {
        return false;
    }
}
