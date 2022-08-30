package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

/**
 * Represents command for bye keyword
 */
public class ByeCommand extends Command {

    /**
     * Instantiates a new ByeCommand
     */
    public ByeCommand() {
        super("bye");
    }

    /**
     * Executes the bye command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Returns true if bye command is executed to
     * end the program
     * @return Returns true if bye command is executed
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
