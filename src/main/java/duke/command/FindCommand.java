package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to execute finding tasks using a keyword
 *
 * @author Nephelite
 * @version 0.2
 */
public class FindCommand extends Command {
    private String word;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a FindCommand
     *
     * @param commands the command split by " " into an array
     * @param tasks the TaskList that Duke is using
     * @param ui the Ui that Duke is using
     * @since 0.3
     */
    public FindCommand(String[] commands, TaskList tasks, Ui ui) throws DukeException {
        assert(commands != null && tasks != null && ui != null);
        if (commands.length > 2) {
            throw new DukeException("this <find> command is invalid.\n"
                    + " Please use command [help] for documentation on proper use.");
        }
        this.word = commands[1];
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * {@inheritDoc}
     *
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException for invalid inputs
     * @since 0.2
     */
    @Override
    public String execute(Storage storage) {
        return ui.readFilteredList(tasks.find(word));
    }
    /**
     * {@inheritDoc}
     *
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
