package seedu.duke;

/**
 * Represents a legal Bye Command operation that can be performed by the user.
 */
public class ByeCommand extends Command {

    public ByeCommand() {

    }

    /**
     * Performs the respective actions with accordance to the command.
     *
     * @param tasklist Tasklist containing an arraylist of tasks.
     * @param ui Ui of the application.
     * @param storage Storage which handles the reading and saving of tasks to hard disk.
     * @param input The user's input in the command line
     * @return a string to be displayed in the GUI when the command is executed.
     * @throws DukeException if command is not found or cannot be executed.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        String output = ui.showGoodbye();
        return output;
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ByeCommand);
    }
}

