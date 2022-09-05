package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * CommandList class which inherits from Command class, handles the 'list' command
 *
 * @author Kavan
 */
public class CommandList extends Command {

    public CommandList(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(" ").append(String.valueOf(i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return listString.toString();
    }
}
