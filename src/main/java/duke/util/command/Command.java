package duke.util.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * Command class which represents a command, e.g. delete, mark, find.
 *
 * @author Kavan
 */
public abstract class Command {
    public String command;

    /**
     * Constructor for Task class.
     *
     * @param command Input command
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Handles given command and returns String representation of handled command.
     *
     * @param taskList User's current TaskList object.
     * @param storedTasks User's current StoredTask object.
     * @return Handled command as a String.
     * @throws DukeException If command is not known.
     */
    public abstract String handleCommand(TaskList taskList, StoredTasks storedTasks) throws DukeException;
}
