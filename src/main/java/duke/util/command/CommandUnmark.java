package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandUnmark extends Command {

    public CommandUnmark(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks saveTasks) {
        Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
        taskList.get(taskNo).markAsUndone();
        return "OK, I've marked this task as not done yet:\n"
                + taskList.get(taskNo);
    }
}
