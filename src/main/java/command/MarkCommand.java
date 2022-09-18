package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task.
 */
public class MarkCommand extends Command {

    /**
     * Constructor for the MarkCommand.
     *
     * @param commandName Description of command.
     * @param exit        Checking if program intends to exit.
     */
    public MarkCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the mark command to mark the task as completed.
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
        try {
            //mark 3 -> 2, because number 3 is actually idx 2
            int number = Integer.parseInt(getCommandName().split(" ")[1]) - 1;
            Task taskToMark = tasks.getTask(number);
            if (taskToMark.checkMarked().equals(" ")) {
                history.addToHistoryList(tasks);
                Task markedTask = taskToMark.toggleCompleted();
                tasks.removeTask(taskToMark);
                tasks.addTaskByIdx(number, markedTask);
                storage.writeToFile(tasks.getTaskList());
                return String.format("Nice! I've marked this task as done:\n  [%s] %s",
                        markedTask.checkMarked(), markedTask.getName());
            } else {
                return "Hey! This task is already marked!";
            }
        } catch (IndexOutOfBoundsException ex) {
            String exceptionErrorMessage = String.format(
                    "Please input the number from 1 - %d", tasks.getSize());
            throw new CleverNotBotException(exceptionErrorMessage, textBox);
        } catch (NumberFormatException ex) {
            throw new CleverNotBotException("Please input a valid number!", textBox);
        }
    }
}
