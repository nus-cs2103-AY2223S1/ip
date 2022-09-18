package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Deadline;
import task.Task;
import task.TaskList;

/**
 * Represents a command used to add an Event Task.
 */
public class AddDeadLineCommand extends Command {

    /**
     * Constructor for the AddDeadLineCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public AddDeadLineCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the add deadline command.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @throws CleverNotBotException Gives an exception when deadline description is empty or not contain /by.
     */
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        String[] desc = getCommandName().split(" ");
        try {
            if (desc.length == 1) {
                throw new CleverNotBotException("Please fill in the description of Deadline!", textBox);
            } else if (!getCommandName().contains("/by")) {
                throw new CleverNotBotException("Please include a /by in your description of Deadline! ", textBox);
            } else {
                String searchWord = " /by";
                int start = "deadline ".length();
                int mid = getCommandName().indexOf(searchWord);
                //deadline desc /by datetime -> "desc"
                String commandName = getCommandName().substring(start, mid);
                String dateTime = getCommandName().substring(mid + searchWord.length() + 1); // to remove the space
                Deadline.validDateTime(dateTime);
                Task newTask = new Deadline(commandName, false, dateTime);
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
            throw new CleverNotBotException("Deadline description must not be empty or must contain /by!", textBox);
        } catch (Exception ex) {
            throw new CleverNotBotException("Incorrect date format! Please enter DD-MM-YYYY HH:mm" +
                    "\nFor example, 22-09-2022 19:40", textBox);
        }

    }


}
