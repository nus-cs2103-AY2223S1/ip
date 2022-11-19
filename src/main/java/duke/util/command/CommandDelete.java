package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

/**
 * CommandDelete class which inherits from Command class, handles the 'delete' command
 *
 * @author Kavan
 */
public class CommandDelete extends Command {
    private static final String deleteMessage = "Noted. I've removed this task:";

    public CommandDelete(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        StringBuilder beforeDeleteString = new StringBuilder();
        beforeDeleteString.append(deleteMessage + "\n").append(taskList.get(taskNumber)).append("\n");
        taskList.remove(taskNumber);
        String afterDeleteString = "Now you have " + String.valueOf(taskList.size()) + " tasks in the list" +
                ".\n";
        return beforeDeleteString.append(afterDeleteString).toString();
    }

}
