package wanya.parser;

import wanya.Storage;
import wanya.task.TaskList;
import wanya.ui.Ui;
import wanya.WanyaException;

import java.time.DateTimeException;

/**
 * Represents a parser that helps to understand user's input commands.
 */
public class Parser {
    /**
     * Checks for empty description of tasks inputted.
     *
     * @param inputs list of Strings that user has inputted.
     * @param command the first word of the user input.
     * @throws WanyaException if description of task is empty.
     */
    public static void checkTask(String[] inputs, String command) throws WanyaException {
        //handle the error where no task name
        String description = inputs.length > 1 ? inputs[1].trim() : "";
        if (inputs.length == 1 || description.startsWith("/at")
                || description.startsWith("/by") || description.equals("")) {
            throw new WanyaException("The description of " + command + " cannot be empty");
        }
    }

    /**
     * Checks for task number to be valid and within the size of task list.
     *
     * @param inputs list of Strings that user has inputted.
     * @param tasks TaskList that contains all the tasks.
     * @return task number if it is valid.
     * @throws WanyaException if task number provided is negative or
     *         greater than the number of tasks in TaskList.
     */
    public static int checkTaskNumber(String[] inputs, TaskList tasks) throws WanyaException {
        //handle error without task number
        if (inputs.length == 1 || inputs[1].trim().equals("")) {
            throw new WanyaException("You didn't put the task number at the back :(.\n" +
                    "Wanya isn't Anya. I can't read your mind!");
        }
        try {
            int index = Integer.parseInt(inputs[1]);
            if (index > tasks.size() || index < 1) {
                throw new WanyaException("Invalid task number!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WanyaException("The task number got to be an integer!");
        }
    }

    /**
     * Instructs the bot to take actions based on the command given by users.
     *
     * @param commandInput String that user inputs.
     * @param tasks TaskList that contains all the tasks.
     * @param ui Ui that handles the strings to print.
     * @return String message to print on GUI.
     */
    public static String parseCommand(String commandInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] inputs = commandInput.split(" ", 2);
            String command = inputs[0];
            String response = "";

            if (commandInput.equals("bye")) {
                return ui.exit();
            } else if (commandInput.equals("list")) {
                return tasks.showTasks();
            } else if (command.equals("mark")) {
                int indexToMark = checkTaskNumber(inputs, tasks);
                response = tasks.get(indexToMark - 1).setComplete();
                storage.save(tasks);
            } else if (command.equals("unmark")) {
                int indexToUnmark = checkTaskNumber(inputs, tasks);
                response = tasks.get(indexToUnmark - 1).setIncomplete();
                storage.save(tasks);
            } else if (command.equals("todo")) {
                checkTask(inputs, command);
                response = tasks.addToDo(inputs[1]);
                storage.save(tasks);
            } else if (command.equals("deadline")) {
                checkTask(inputs, command);
                try {
                    response = tasks.addDeadline(inputs[1]);
                    storage.save(tasks);
                } catch (DateTimeException e) {
                    return ui.showDateTimeFormat("D");
                }
            } else if (command.equals("event")) {
                checkTask(inputs, command);
                try {
                    response = tasks.addEvent(inputs[1]);
                    storage.save(tasks);
                } catch (DateTimeException e) {
                    return ui.showDateTimeFormat("E");
                }
            } else if (command.equals("delete")) {
                int index = checkTaskNumber(inputs, tasks);
                response = tasks.deleteTask(index);
                storage.save(tasks);
            } else if (command.equals("find")) {
                checkTask(inputs, command);
                return tasks.findTasks(inputs[1]);
            } else {
                throw new WanyaException("I am sorry. Wanya doesn't like to study " +
                        "so Wanya don't know what that means.");
            }
            return response;
        } catch (WanyaException e) {
            return ui.showErrorMessage(e);
        }
    }
}


