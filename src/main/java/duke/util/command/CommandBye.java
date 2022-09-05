package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandBye extends Command {
    private static final String byeMessage = "Bye. Hope to see you again soon!";

    public CommandBye(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        storedTasks.save(taskList);
        return byeMessage;
    }
}
