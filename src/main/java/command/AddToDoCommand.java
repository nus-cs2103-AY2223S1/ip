package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.util.Arrays;

/**
 * Represents a command used to add a ToDo Task.
 */
public class AddToDoCommand extends Command {

    /**
     * Constructor for the AddToDoCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public AddToDoCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the add ToDo command.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @return A String type response.
     * @throws CleverNotBotException
     */
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        String[] desc = getCommandName().split(" ");
        try {
            if (desc.length != 1) {
                //removes the command name "todo" eg. [todo,borrow,book] -> [borrow,book]
                String[] descOnly = Arrays.copyOfRange(desc, 1, desc.length);
                String joinDesc = String.join(" ", descOnly);
                Task newTask = new ToDo(joinDesc, false);
                history.addToHistoryList(tasks);
                tasks.addTask(newTask);
                storage.writeToFile(tasks.getTaskList());
                return String.format(
                        "Got it. I've added this task:"
                                + "\n  %s"
                                + "\nNow you have %d tasks in the list."
                        , newTask.toString(), tasks.getSize());
            } else {
                throw new CleverNotBotException("Please fill in the description of ToDo!", textBox);
            }
        } catch (CleverNotBotException e) {
            throw new CleverNotBotException("Please fill in the description of ToDo!", textBox);
        }

    }

}
