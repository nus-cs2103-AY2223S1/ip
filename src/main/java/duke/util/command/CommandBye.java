package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;
import javafx.application.Platform;

public class CommandBye extends Command{

    public CommandBye(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        storedTasks.save(taskList);
        return "Buh Bye. Pls come back again ah!";
    }
}
