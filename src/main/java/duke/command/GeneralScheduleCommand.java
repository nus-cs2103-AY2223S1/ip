package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.scheduler.GeneralSchedule;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Constructs an instance of GeneralScheduleCommand which handles GeneralSchedules
 */
public class GeneralScheduleCommand extends Command {
    public static final String COMMAND_ID = "SCHEDULE_GENERAL";
    public GeneralScheduleCommand() {
        super();
    }

    /**
     * Returns a string of the task that has just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return string of executed task information.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        TaskList generalSchedule = new GeneralSchedule(taskList).getSchedule();
        if (generalSchedule.getTaskList().isEmpty()) {
            return "Schedule is currently empty";
        } else {
            String output = String.format(
                    "Here is your general schedule !", generalSchedule.getTaskList().size());
            String result = generalSchedule.listTasks();
            return String.format("%s\n%s", output, result);
        }
    }
}
