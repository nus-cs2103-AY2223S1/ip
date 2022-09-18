package command;

import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command used to find all tasks in TaskList.
 */
public class FindCommand extends Command {

    /**
     * Constructor for the FindCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public FindCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the add event command.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception when event's description is empty or not contain /at.
     */
    @Override
    public String run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        String[] desc = getCommandName().split(" ", 2);
        if (desc.length == 1) {
            throw new CleverNotBotException("Please fill in the search word!", textBox);
        }
        ArrayList<Task> result = tasks.findTasks(desc[1].trim());
        StringBuilder op = new StringBuilder();
        if (result.size() == 0) {
            op.append(String.format("No task contains %s", desc[1].trim()));
        } else {
            int counter = 1;
            op.append("Here are the matching tasks in your list:\n");
            for (Task task : result) {
                op.append(counter++);
                op.append(".");
                op.append(task.toString());
                op.append("\n");
            }
        }
        return op.toString();
    }
}
