package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandBye extends Command{

    public CommandBye(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        storedTasks.save(taskList);
        return "Bye. Hope to see you again!";
    }
}
