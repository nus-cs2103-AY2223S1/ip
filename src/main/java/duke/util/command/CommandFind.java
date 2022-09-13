package duke.util.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandFind extends Command {

    public CommandFind(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        StringBuilder description = new StringBuilder();
        if (taskList.size() == 0) {
            return "You have no task in your list.\n";
        } else {
            TaskList filteredTask = taskList.filter(command.split(" ")[1]);
            StringBuilder firstMessage = new StringBuilder();
            firstMessage.append("Here are the matching tasks in your list:\n");
            for (int j = 0; j < filteredTask.size(); j++) {
                description.append("").append(String.valueOf(j + 1)).append(". ").append(filteredTask.get(j))
                        .append("\n");
            }

            return firstMessage.append(description).toString();
        }
    }
}
