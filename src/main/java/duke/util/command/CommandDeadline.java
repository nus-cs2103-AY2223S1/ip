package duke.util.command;

import duke.DukeException;
import duke.task.Deadlines;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.DateAndTimeParser;
import duke.util.StoredTasks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CommandDeadline class which inherits from Command class, handles the 'deadline' command
 *
 * @author Kavan
 */
public class CommandDeadline extends Command {
    private final String deadlineMessage = "Got it. I've added this task:\n";

    public CommandDeadline(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) throws DukeException {
        ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
        int posOfBy = commandDelimited.indexOf("/by");
        StringBuilder description = new StringBuilder();
        StringBuilder dateTime = new StringBuilder();
        for (int i = 1; i < commandDelimited.size(); i++) {
            if (i < posOfBy) {
                description.append(commandDelimited.get(i));
                if (i != posOfBy - 1) {
                    description.append(" ");
                }
            } else if (i > posOfBy) {
                dateTime.append(commandDelimited.get(i));
                if (i != commandDelimited.size() - 1) {
                    dateTime.append(" ");
                }
            }
        }

        Task deadline = new Deadlines(description.toString(), dateTime.toString(),
                DateAndTimeParser.validateAndParse(dateTime.toString()));
        taskList.add(deadline);

        return deadlineMessage + deadline + "\nNow you have " + String.valueOf(taskList.size()) + " tasks " +
                "in the list.";
    }
}
