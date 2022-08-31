package duke.command;

import duke.task.TaskList;
import java.time.LocalDate;

/**
 * Date command class to find all the tasks on a give date.
 */
public class DateCommand extends Command{
    private LocalDate localDate;

    /**
     * Constructor of date command.
     *
     * @param date The date to find.
     */
    public DateCommand(LocalDate date) {
        this.localDate = date;
    }

    /**
     * Prints all tasks on a given date.
     *
     * @param tasks The tasks to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.printTasksOnSpecificDate(this.localDate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
