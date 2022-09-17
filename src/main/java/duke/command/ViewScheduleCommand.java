package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.date.DateTimeParse;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.TaskList;

/**
 * Represents a command to view the scheduled task on a specified date.
 */
public class ViewScheduleCommand extends Command {
    private static final String TODAY_SCHEDULE_TASKS =
            "let's see... you have %s tasks on your schedule on %s, let's get to work! [_(`▿´)_]";
    private static final String NO_TASKS_SCHEDULE = "wow wow wow, looks like your schedule for %s is empty!"
            + "\n\ntake a break? do some other work? or even add some new tasks? just let bobo know! (✿◠‿◠)";
    private final LocalDate date;

    /**
     * Constructs a ViewScheduleCommand.
     *
     * @param userInput The userInput without the schedule command.
     * @throws DukeException If the date could not be parsed.
     */
    public ViewScheduleCommand(String userInput) throws DukeException {
        if (userInput.replace(" ", "").isEmpty()) {
            date = LocalDate.now();
            return;
        }
        date = DateTimeParse.parseDateTime(userInput.substring(1)).toLocalDate();
    }

    /**
     * Returns the list of tasks scheduled for the specified date. If no date is specified,
     * the current date will be assumed.
     *
     * @param tasks The task list the task is to be added to.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return Type task Response containing the list of scheduled tasks and a response message.
     */
    @Override
    public Response<TaskList> execute(TaskList tasks, Storage storage) {
        TaskList schedule = tasks.filter(task -> task.isActive(date));
        int numTasks = schedule.size();
        String responseMessage = numTasks > 0
                ? String.format(TODAY_SCHEDULE_TASKS, numTasks, date)
                : String.format(NO_TASKS_SCHEDULE, date);
        return new Response<>(ResponseType.LIST, responseMessage, schedule);
    }
}
