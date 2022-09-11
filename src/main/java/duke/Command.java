package duke;

import java.util.Scanner;

/**
 * Enum class used to store the command flags and execution logic of the various available commands.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public enum Command {
    /**
     * duke.Command to list all available Tasks
     */
    list,
    /**
     * duke.Command to create a new duke.Todo
     */
    todo,
    /**
     * duke.Command to create a new duke.Deadline
     */
    deadline,
    /**
     * duke.Command to create a new duke.Event
     */
    event,
    /**
     * duke.Command to exit the system
     */
    bye,
    /**
     * duke.Command to delete a duke.Task
     */
    delete,
    /**
     * duke.Command to mark a task as complete
     */
    mark,
    /**
     * duke.Command to mark a task as incomplete
     */
    unmark,
    /**
     * duke.Command to find a task
     */
    find;

    private static DukeException noNumberException = new DukeException("Sorry, no number was detected");

    /**
     * Execute the greet command and welcome the user.
     */
    public static String greet() {
        return "Hello from Duke!\nWhat can I do for you?";
    }

    /**
     * Execute the exit command and end the program.
     *
     * @param scanner The scanner object that needs to be closed
     */
    public static void exit(Scanner scanner) {
        System.out.println(" Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * Execute the command to list all available tasks.
     *
     * @param allTasks the list to store all tasks created by the user
     */
    public static String listTasks(AllTasksList allTasks) {
        return allTasks.listAllTasks();
    }

    /**
     * Execute the command to chat with the user.
     *
     * @param userInput  the scanner object to get user input.
     * @param allTasks the list to store all tasks created by the user.
     * @return the response from the bot
     */
    public static String chat(String userInput, AllTasksList allTasks) {
        String botOutput = "";
        try {
            botOutput = Command.parseAndExecuteCommand(userInput, allTasks);
            Storage.storeTasks(allTasks);
        } catch (DukeException e) {
            botOutput = e.getMessage();
        } finally {
            return botOutput;
        }
    }

    /**
     * Execute the command to mark a task as complete.
     *
     * @param commandArray a string array of commands to be parsed for more information
     * @param allTasks     the list to store all tasks created by the user
     * @throws DukeException
     */
    public static String markTask(String[] commandArray, AllTasksList allTasks) throws DukeException {
        try {
            if (commandArray.length <= 1) {
                throw noNumberException;
            }

            int index = Integer.parseInt(commandArray[1]) - 1;
            return allTasks.markTask(index);
        } catch (NumberFormatException e) {
            throw noNumberException;
        }
    }

    /**
     * Execute the command to mark a task as incomplete.
     *
     * @param commandArray a string array of commands to be parsed for more information
     * @param allTasks     the list to store all tasks created by the user
     * @throws DukeException
     */
    public static String unMarkTask(String[] commandArray, AllTasksList allTasks)
            throws DukeException {
        try {
            if (commandArray.length <= 1) {
                throw noNumberException;
            }

            int index = Integer.parseInt(commandArray[1]) - 1;
            return allTasks.unMarkTask(index);
        } catch (NumberFormatException e) {
            throw noNumberException;
        }
    }

    /**
     * Execute the command to delete a task.
     *
     * @param commandArray a string array of commands to be parsed for more information
     * @param allTasks     the list to store all tasks created by the user
     * @throws DukeException
     */
    public static String delete(String[] commandArray, AllTasksList allTasks)
            throws DukeException {
        try {
            if (commandArray.length <= 1) {
                throw noNumberException;
            }

            int index = Integer.parseInt(commandArray[1]) - 1;
            return allTasks.delete(index);
        } catch (NumberFormatException e) {
            throw noNumberException;
        }
    }

    /**
     * utility method used to parse the and execute the user command.
     *
     * @param userInput the raw input string the user entered into the chatbot
     * @param allTasks  the Object storing all the tasks created by the user
     * @throws DukeException
     * @throws NumberFormatException
     */
    public static String parseAndExecuteCommand(String userInput, AllTasksList allTasks) throws DukeException {
        String[] commandArray = userInput.split(" ", 2);
        assert commandArray.length <= 2 : "command array should be at most 2 in length";
        String command = commandArray[0];
        try {
            switch (Command.valueOf(command)) {
            case bye:
                return "Goodbye!";
            case list:
                return Command.listTasks(allTasks);
            case find:
                return allTasks.find(commandArray[1]);
            case mark:
                return Command.markTask(commandArray, allTasks);
            case unmark:
                return Command.unMarkTask(commandArray, allTasks);
            case delete:
                return Command.delete(commandArray, allTasks);
            default:
                Task newTask = Task.createTask(commandArray);
                return allTasks.addTask(newTask);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException();
        }
    }
}
