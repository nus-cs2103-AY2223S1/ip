package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to show what is in the list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for the ListCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public ListCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the list command to list out every task in list.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        if (tasks.getSize() < 1) {
            return "There is no task currently assigned.";
        } else {
            int counter = 1;
            StringBuilder op = new StringBuilder();
            for (Task task : tasks.getTaskList()) {
                op.append(counter++);
                op.append(".");
                op.append(task.toString());
                op.append("\n");
            }
            return op.toString();
        }
    }
}
