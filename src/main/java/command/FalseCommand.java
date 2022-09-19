package command;

import storage.Storage;
import task.NotesList;
import task.TaskList;
import ui.Ui;
import exception.DukeException;

/**
 * Represents a FalseCommand that inherits from
 * the abstract class Command.
 */
public class FalseCommand extends Command {
    protected String commandLine;

    /**
     * Constructor for this FalseCommand that takes in a
     * commandLine as a String.
     *
     * @param commandLine Command from the user.
     */
    public FalseCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * Returns a boolean false to show that this
     * is not the last command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Throws a DukeException to tell the user that the command is
     * invalid.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     * @throws DukeException Always.
     */
    @Override
    public String execute(TaskList taskList, NotesList notesList, Ui ui, Storage storage) throws DukeException {
        try {
            throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        } catch (DukeException e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }
}
