package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * CommandFind class which inherits from Command class, handles the 'find' command
 *
 * @author Kavan
 */
public class CommandFind extends Command {
    private static final String findMessage = "Here are the matching tasks in your list:\n";

    public CommandFind(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        String keyword = command.split(" ")[1];
        StringBuilder findString = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).containsKeyword(keyword)) {
                counter++;
                findString.append(String.valueOf(counter)).append(". ").append(taskList.get(i));
            }
        }
        return findMessage + findString;
    }
}
