package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * CommandMark class which inherits from Command class, handles the 'mark' command
 *
 * @author Kavan
 */
public class CommandMark extends Command {
    private static final String markMessage = "Nice! I've marked this task as done:";

    public CommandMark(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        int taskNumber = Integer.parseInt(this.command.split(" ")[1]) - 1;
        taskList.get(taskNumber).markAsDone();
        return markMessage + taskList.get(taskNumber);
    }
}
