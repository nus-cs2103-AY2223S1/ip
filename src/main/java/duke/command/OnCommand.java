package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The OnCommand class represents a command
 * that will find tasks on a specified date from duke.Duke's task list.
 */
public class OnCommand extends Command {
    /** The date at/by which tasks occur to be checked. */
    private final LocalDate date;

    /**
     * Constructs a new OnCommand.
     *
     * @param date The specified date to check.
     */
    public OnCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Finds a task in Duke that happens on the date of this instance of OnCommand.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message with all the tasks on a specified date in Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingList = tasks.getAllOnDate(date);
        return ui.showOnDate(matchingList);
    }
}
