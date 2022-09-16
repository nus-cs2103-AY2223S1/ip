package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CommandUpdate class which inherits from Command class, handles the 'update' command
 *
 * @author Kavan
 */
public class CommandUpdateDescription extends Command {
    private static final String updateMessage = "Noted. I've updated the description of this task:\n";

    public CommandUpdateDescription(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
        StringBuilder description = new StringBuilder();
        for (int i = 2; i < commandDelimited.size(); i++) {
            description.append(commandDelimited.get(i));
            if (i != commandDelimited.size() - 1) {
                description.append(" ");
            }
        }
        taskList.get(taskNumber).updateDescription(description.toString());
        return updateMessage + taskList.get(taskNumber);
    }

}
