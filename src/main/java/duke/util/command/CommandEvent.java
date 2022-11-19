package duke.util.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Events;
import duke.util.StoredTasks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CommandEvent class which inherits from Command class, handles the 'event' command
 *
 * @author Kavan
 */
public class CommandEvent extends Command {
    private final String eventMessage = "Got it. I've added this task:\n";

    public CommandEvent(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) throws DukeException {
        ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
        int posOfAt = commandDelimited.indexOf("/at");
        StringBuilder description = new StringBuilder();
        StringBuilder specificTime = new StringBuilder();
        for (int i = 1; i < commandDelimited.size(); i++) {
            if (i < posOfAt) {
                description.append(commandDelimited.get(i));
                if (i != posOfAt - 1) {
                    description.append(" ");
                }
            } else if (i > posOfAt) {
                specificTime.append(commandDelimited.get(i));
                if (i != commandDelimited.size() - 1) {
                    specificTime.append(" ");
                }
            }
        }
        Task event = new Events(description.toString(), specificTime.toString());
        taskList.add(event);

        String afterEventMessage =
                eventMessage + event + "\nNow you have " + String.valueOf(taskList.size()) + " tasks " +
                        "in the list.";
        return afterEventMessage;
    }
}
