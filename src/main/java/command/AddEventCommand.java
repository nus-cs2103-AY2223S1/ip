package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Event;
import task.Task;
import task.TaskList;

/**
 * Represents a command used to add an Event Task.
 */
public class AddEventCommand extends Command {

    /**
     * Constructor for the AddEventCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public AddEventCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the add event command.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @throws CleverNotBotException Gives an exception when event's description is empty or not contain /at.
     */
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        String[] desc = getCommandName().split(" ");
        try {
            if (desc.length == 1) {
                throw new CleverNotBotException("Please fill in the description of Event!", textBox);
            } else if (!getCommandName().contains("/at")) {
                throw new CleverNotBotException("Please include a /at in your description of Deadline! ", textBox);
            } else {
                String searchWord = " /at";
                int start = "event ".length();
                int mid = getCommandName().indexOf(searchWord);
                String commandName = getCommandName().substring(start, mid);
                String at = getCommandName().substring(mid + searchWord.length() + 1); // to remove the space;
                Task newTask = new Event(commandName, false, at);
                history.addToHistoryList(tasks);
                tasks.addTask(newTask);
                storage.writeToFile(tasks.getTaskList());
                return String.format(
                        "Got it. I've added this task:"
                                + "\n  %s"
                                + "\nNow you have %d tasks in the list."
                        , newTask.toString(), tasks.getSize());
            }
        } catch (CleverNotBotException e) {
            throw new CleverNotBotException("Event description must not be empty or must contain /at!", textBox);
        }

    }

}
