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
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingList = tasks.allOnDate(date);
        ui.showOnDate(matchingList);
    }
}
