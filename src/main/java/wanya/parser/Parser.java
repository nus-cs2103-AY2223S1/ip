package wanya.parser;

import wanya.Storage;
import wanya.task.TaskList;
import wanya.WanyaException;
import wanya.ui.Ui;


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
    private static void checkTaskDescription(String[] inputs, String command) throws WanyaException {
        //handle the error where no task name
        String description = inputs.length > 1 ? inputs[1].trim() : "";

        boolean doesDescriptionStartWithAt = description.startsWith("/at");
        boolean doesDescriptionStartWithBy = description.startsWith("/by");
        boolean doesDescriptionStartWithAft = description.startsWith("/aft");
        boolean doesDescriptionStartWithKeyword = doesDescriptionStartWithAt || doesDescriptionStartWithBy
                || doesDescriptionStartWithAft;
        boolean isDescriptionEmpty = description.equals("");
        boolean isInvalidDescription = (doesDescriptionStartWithKeyword || isDescriptionEmpty);

        if (isInvalidDescription) {
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
    private static int checkTaskNumber(String[] inputs, TaskList tasks) throws WanyaException {
        //handle error without task number
        if (inputs.length == 1 || inputs[1].trim().equals("")) {
            throw new WanyaException("You didn't put the task number at the back :(.\n"
                    + "Wanya isn't Anya. I can't read your mind!");
        }
        try {
            int index = Integer.parseInt(inputs[1]);
            boolean isWithinSize = index > tasks.size();
            boolean isSmallerThanOne = index < 1;
            if (isWithinSize || isSmallerThanOne) {
                throw new WanyaException("Invalid task number!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WanyaException("The task number got to be an integer!");
        }
    }

    /**
     * Handles the Mark and Unmark commands for completeness of each tasks.
     *
     * @param command the first word of the user input.
     * @param inputs list of Strings that user has inputted.
     * @param tasks TaskList that contains all the tasks.
     * @param storage Storage that stores the tasks in hard drive.
     * @return String message to be printed to gui whether task is marked successfully.
     * @throws WanyaException if task number provided is invalid or no task number provided.
     */
    private static String handleMarkingCommands(String command, String[] inputs,
                                                TaskList tasks, Storage storage) throws WanyaException {

        int indexOfTask = checkTaskNumber(inputs, tasks);
        String response = command.equals("mark")
                          ? tasks.get(indexOfTask).setComplete()
                          : tasks.get(indexOfTask).setIncomplete();
        storage.save(tasks);
        return response;
    }

    /**
     * Handles the add task commands to add different type of tasks to task list.
     *
     * @param command the first word of the user input.
     * @param inputs list of Strings that user has inputted.
     * @param tasks TaskList that contains all the tasks.
     * @param storage Storage that stores the tasks in hard drive.
     * @return String message to be printed to gui whether task is added successfully.
     * @throws WanyaException if invalid description is provided to add task.
     */
    private static String handleAddTaskCommands(String command, String[] inputs,
                                                TaskList tasks, Storage storage) throws WanyaException {
        checkTaskDescription(inputs, command);
        String response;
        if (command.equals("todo")) {
            response = tasks.addToDo(inputs[1]);
        } else if (command.equals("deadline") || command.equals("event")) {
            response = tasks.addTaskWithDate(command, inputs[1]);
        } else {
            assert command.equals("period") : "Invalid command entered!";
            response = tasks.addPeriod(inputs[1]);
        }
        storage.save(tasks);
        return response;
    }

    /**
     * Instructs the bot to take actions based on the command given by users.
     *
     * @param commandInput String that user inputs.
     * @param tasks TaskList that contains all the tasks.
     * @param ui Ui that handles the strings to print.
     * @param storage Storage that stores the tasks in hard drive.
     * @return String message to print on GUI.
     */
    public static String parseCommand(String commandInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] inputs = commandInput.split(" ", 2);
            assert inputs.length > 0 : "Invalid command inputted! Command cannot be empty";
            String command = inputs[0];
            String response = "";

            if (commandInput.equals("bye")) {
                return ui.exit();
            } else if (commandInput.equals("list")) {
                return tasks.showTasks();
            } else if (command.equals("mark") || command.equals("unmark")) {
               response = handleMarkingCommands(command, inputs, tasks, storage);
            } else if (command.equals("todo") || command.equals("deadline")
                    || command.equals("event") || command.equals("period")) {
                response = handleAddTaskCommands(command, inputs, tasks, storage);
            } else if (command.equals("delete")) {
                int index = checkTaskNumber(inputs, tasks);
                response = tasks.deleteTask(index);
                storage.save(tasks);
            } else if (command.equals("find")) {
                checkTaskDescription(inputs, command);
                return tasks.findTasks(inputs[1]);
            } else {
                throw new WanyaException("I am sorry. Wanya doesn't like to study "
                        + "so Wanya don't know what that means.");
            }
            return response;
        } catch (WanyaException e) {
            return ui.showErrorMessage(e);
        }
    }
}


