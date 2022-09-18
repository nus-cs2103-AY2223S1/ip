package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.Deadline;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to add a Deadline object.
 */
public class AddDeadlineCommand extends Command {
    public static final String KEYWORD = "deadline";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nAdds an deadline with a given date and time of deadline."
        + "\n\nRequired: /by The delimiter to detect the date and time"
        + "\n\nExample: deadline submit iP /by 10 Oct 2021 23:59";
    private Deadline deadline;
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param deadline The deadline to be added
     * @param helpShown Whether to show help or not
     */
    private AddDeadlineCommand(Deadline deadline, boolean helpShown) {
        this.deadline = deadline;
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to add a deadline.
     *
     * @param deadline The deadline to be added
     */
    public AddDeadlineCommand(Deadline deadline) {
        this(deadline, false);
    }

    /**
     * Overloads the constructor method to show help.
     */
    public AddDeadlineCommand() {
        this(null, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a Deadline task class to the task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.helpShown) {
            return HELP_MESSAGE;
        } else {
            tasks.add(this.deadline);
            return "There, we have a new deadline:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.";
        }
    }
}
