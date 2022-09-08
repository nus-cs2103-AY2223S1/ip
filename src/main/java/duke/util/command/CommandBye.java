package duke.util.command;

import duke.task.TaskList;
import duke.util.SaveTasks;

public class CommandBye extends Command{

    public CommandBye(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, SaveTasks saveTasks) {
        saveTasks.save(taskList);
        return "Bye. Hope to see you again!";
    }
}
