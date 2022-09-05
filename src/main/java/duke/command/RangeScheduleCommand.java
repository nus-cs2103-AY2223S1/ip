package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.Ui;
import duke.scheduler.RangeSchedule;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Class that encompasses Schedule for a Date range
 */
public class RangeScheduleCommand extends Command {
    public static final String COMMAND_ID = "SCHEDULE_RANGE";
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Creates an instance of RangeScheduleCommand
     * @param startDate start of the LocalDateTime range
     * @param endDate end of the LocalDateTime range
     */
    public RangeScheduleCommand(LocalDateTime startDate, LocalDateTime endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string of the task that has just been executed
     * @param taskList list of tasks
     * @param ui
     * @param storage
     * @return string of executed task information.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        TaskList rangeSchedule = new RangeSchedule(taskList, startDate, endDate).getSchedule();
        if (rangeSchedule.getTaskList().isEmpty()) {
            return String.format("Schedule is currently empty for dates between %s and %s !", startDate, endDate);
        } else {
            String finalEndDate = endDate.equals(LocalDateTime.MAX) ? "beyond" : endDate.toString();
            String output = String.format(
                    "Here is your schedule for dates between %s and %s !",
                    startDate, finalEndDate);
            String result = rangeSchedule.listTasks();
            return String.format("%s\n%s", output, result);
        }
    }
}
