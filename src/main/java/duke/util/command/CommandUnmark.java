package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * CommandUnmark class which inherits from Command class, handles the 'unmark' command
 *
 * @author Kavan
 */
public class CommandUnmark extends Command {
    private static final String unmarkMessage = "OK, I've marked this task as not done yet:";

    public CommandUnmark(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        int taskNumber = Integer.parseInt(this.command.split(" ")[1]) - 1;
        taskList.get(taskNumber).markAsUndone();
        return unmarkMessage + taskList.get(taskNumber);
    }
}
