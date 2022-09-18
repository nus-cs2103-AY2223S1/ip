package command;

import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for the DeleteCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public DeleteCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the command to delete a specific task in task list (tasks).
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception if there is not a valid number or if task does not exist.
     */
    public String run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        try {
            String[] desc = getCommandName().split(" ");
            if (desc.length <= 1) {
                throw new CleverNotBotException("Invalid arguments. Please enter a number!", textBox);
            }
            Task deletedTask = tasks.getTask(Integer.parseInt(desc[1]) - 1); // Task.Task 3 is in idx 2
            tasks.removeTask(deletedTask);
            storage.writeToFile(tasks.getTaskList());
            return String.format(
                    "Noted. I've removed this task:" +
                            "\n  %s" +
                            "\nNow you have %d tasks in the list."
                    , deletedTask.toString(), tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new CleverNotBotException("The task does not exist!", textBox);
        } catch (NumberFormatException ex) {
            throw new CleverNotBotException("Input a valid number!", textBox);
        }

    }
}
