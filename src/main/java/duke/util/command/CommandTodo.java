package duke.util.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todos;
import duke.util.StoredTasks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CommandTodo class which inherits from Command class, handles the 'todo' command
 *
 * @author Kavan
 */
public class CommandTodo extends Command {
    private final String todoMessage = "Got it. I've added this task:\n";

    public CommandTodo(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) throws DukeException {
        ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
        if (commandDelimited.size() == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commandDelimited.size(); i++) {
            description.append(commandDelimited.get(i));
            if (i != commandDelimited.size() - 1) {
                description.append(" ");
            }
        }
        Task todo = new Todos(description.toString());
        taskList.add(todo);
        return todoMessage + todo + "\nNow you have " + String.valueOf(taskList.size()) + " tasks " +
                "in the list.";
    }
}
