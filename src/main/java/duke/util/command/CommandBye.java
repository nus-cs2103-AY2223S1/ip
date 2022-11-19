package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * CommandBye class which inherits from Command class, handles the 'bye' command
 *
 * @author Kavan
 */
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
