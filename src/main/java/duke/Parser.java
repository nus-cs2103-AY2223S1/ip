package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser class allows the Duke program
 * to parse user inputs into commands.
 *
 * @author Gerald Teo Jin Wei
 * @version 0.1
 * @since 2022-08-28
 */
public class Parser {
    private Parser() {}

    /**
     * Converts user date input in YYYY-MM-DD format
     * to MMM d YYY format and then returns the newly formatted date string.
     * (e.g 2019-10-02 to OCT 2 2019)
     * @param date string of date in YYYY-MM-DD format
     * @return string of date in the MMM d YYYY format
     * @throws DateTimeParseException if user date input is not in YYYY-MM-DD format
     */
    public static String parseDate(String date) throws DateTimeParseException {
        LocalDate d = LocalDate.parse(date);
        String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    /**
     * Converts the user's raw string input into a command, executes the command
     * and then returns Ui's string response to command.
     * When an invalid input is received, an error string message is returned.
     * @param rawInput user's string input
     * @param taskList user's current tasklist
     * @param ui ui for the Duke program to interact/print outputs to the user
     * @return string that is either printed out by Ui or is an error message
     */
    public static String parseCommand(String rawInput, TaskList taskList, Ui ui) {
        try {
            String trimmedInput = rawInput.replaceAll("\\s{2,}", " ").trim();
            String[] splitStr = trimmedInput.split(" ");
            String commandAction = splitStr[0];

            if (splitStr.length == 1) {
                return parseCommandWithoutCommandBody(commandAction, taskList, ui);
            }

            String commandBody = trimmedInput.substring(commandAction.length() + 1);
            return parseCommandWithCommandBody(commandAction, commandBody, taskList, ui);

        } catch (DukeException de) {
            return de.getMessage();
        } catch (DateTimeParseException dtpe) {
            return "Deadline date is entered incorrectly. Enter date in YYYY-MM-DD format (e.g 2019-10-02)";
        } catch (NumberFormatException nfe) {
            return "Invalid task number";
        }
    }

    /**
     * Executes the command action and returns Ui's
     * string response to the command without command body
     * When an invalid input is received, an error string message is returned.
     * @param commandAction word that determines which type of command to execute
     * @param taskList user's current tasklist
     * @param ui ui for the Duke program to interact/print outputs to the user
     * @return String that is either printed out by Ui or is an error message
     * @throws DukeException if user enters invalid input
     */
    private static String parseCommandWithoutCommandBody(String commandAction,
                                                         TaskList taskList, Ui ui) throws DukeException {
        switch (commandAction) {
        case "bye":
        case "exit":
        case "quit":
            return ui.quit();
        case "list":
            return ui.listAllTasks(taskList);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but "
                + "I don't know what that means :-(\n");
        }
    }

    /**
     * Parses the command body, executes the command and
     * then returns the Ui's string response to the command.
     * When an invalid input is received, an error string message is returned.
     * @param commandAction word that determines which type of command to execute
     * @param commandBody the user's input excluding the command action
     * @param taskList user's current tasklist
     * @param ui ui for the Duke program to interact/print outputs to the user
     * @return String that is either printed out by Ui or is an error message
     * @throws DukeException if user enters invalid input
     */
    private static String parseCommandWithCommandBody(String commandAction, String commandBody,
                                                      TaskList taskList, Ui ui) throws DukeException {
        int commandBodyWordCount = commandBody.split(" ").length;
        switch (commandAction) {
        case "mark":
            Task taskToMark = getTaskToEdit(commandBody, commandBodyWordCount, taskList);
            taskToMark.mark();
            return ui.markTask(taskToMark);
        case "unmark":
            Task taskToUnmark = getTaskToEdit(commandBody, commandBodyWordCount, taskList);
            taskToUnmark.unmark();
            return ui.printUnmarkTaskMessage(taskToUnmark);
        case "delete":
        case "rm":
            Task taskToDelete = getTaskToEdit(commandBody, commandBodyWordCount, taskList);
            taskList.deleteTask(taskToDelete);
            return ui.printDeleteTaskMessage(taskList, taskToDelete);
        case "find":
        case "f":
            List<Task> taskListWithKeyWord = getTaskListWithKeyword(commandBody, taskList);
            return ui.printTasksWithKeyword(taskListWithKeyWord);
        case "todo":
        case "t":
            Task toDoToAdd = getToDoToAdd(commandBody, commandBodyWordCount);
            taskList.addTask(toDoToAdd);
            return ui.printAddTaskMessage(taskList, toDoToAdd);
        case "deadline":
        case "d":
            Task deadlineToAdd = getDeadlineToAdd(commandBody, commandBodyWordCount);
            taskList.addTask(deadlineToAdd);
            return ui.printAddTaskMessage(taskList, deadlineToAdd);
        case "event":
        case "e":
            Task eventToAdd = getEventToAdd(commandBody, commandBodyWordCount);
            taskList.addTask(eventToAdd);
            return ui.printAddTaskMessage(taskList, eventToAdd);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but "
                + "I don't know what that means :-(\n");
        }
    }

    /**
     * Checks if the user's chosen task index number is valid.
     * Returns true if valid and throws DukeException when invalid.
     * @param index user's chosen task index number
     * @param taskList user's current tasklist
     * @return true when task index number is valid
     * @throws DukeException if invalid task number is entered
     */
    private static boolean isValidIndex(int index, TaskList taskList) throws DukeException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new DukeException("OOPS!!! Index " + index
                    + " is not valid. Please enter a task number from 1 - " + taskList.getSize());
        }
        return true;
    }

    /**
     * Returns task from the task list to be edited (marked, unmarked, removed).
     * @param commandBody user's string input without the command action
     * @param commandBodyWordCount number of words in command body separated by " "
     * @param taskList user's current tasklist
     * @return task from the user's task list to be edited
     * @throws DukeException if an invalid task number is entered
     * @throws NumberFormatException if the command body is not a number
     */
    private static Task getTaskToEdit(String commandBody, int commandBodyWordCount,
                                        TaskList taskList) throws NumberFormatException, DukeException {
        int index = Integer.parseInt(commandBody);
        if (commandBodyWordCount != 1 || !isValidIndex(index, taskList)) {
            return null;
        }
        return taskList.getTask(index - 1);
    }



    /**
     * Returns a ToDo task to be added with
     * description from the command body
     * @param commandBody  user's string input without the command action
     * @param commandBodyWordCount number of words in the command body separated by " "
     * @return ToDo task with description from command body
     * @throws DukeException if command body is empty
     */
    private static Task getToDoToAdd(String commandBody, int commandBodyWordCount) throws DukeException {
        if (commandBodyWordCount < 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        return new ToDo(commandBody);
    }

    /**
     * Returns a Deadline task to be added with description
     * and date from the command body
     * @param commandBody user's string input without the command action
     * @param commandBodyWordCount number of words in the command body sepaated by " "
     * @return Deadline task with description and date from command body
     * @throws DukeException if invalid command body
     */
    private static Task getDeadlineToAdd(String commandBody,
                                         int commandBodyWordCount) throws DukeException {
        if (commandBodyWordCount < 3) {
            throw new DukeException("OOPS!!! Add an deadline in the following format: "
                    + "\n\ndeadline <task description> /by <YYYY-MM-DD>\n");
        }
        if (commandBody.indexOf(" /by") - 1 < 0) {
            throw new DukeException("OOPS!!! Please set date of deadline with /by.\n");
        }
        String description = commandBody.substring(0, commandBody.indexOf("/by") - 1);
        String date = commandBody.substring(commandBody.indexOf("/by") + "/by ".length());
        String formattedDate = Parser.parseDate(date);
        return new Deadline(description, formattedDate);
    }

    /**
     * Returns a Event task to be added with description
     * and date from the command body
     * @param commandBody user's string input without the command action
     * @param commandBodyWordCount number of words in the command body separated by " "
     * @return Event task with description and date from command body
     * @throws DukeException if invalid command body
     */
    private static Task getEventToAdd(String commandBody, int commandBodyWordCount) throws DukeException {
        if (commandBodyWordCount < 3) {
            throw new DukeException("OOPS!!! Add an event in the following format: "
                    + "\n\nevent <task description> /at <date>\n");
        }
        if (commandBody.indexOf(" /at") - 1 < 0) {
            throw new DukeException("OOPS!!! Please set date of at with /at.\n");
        }
        String description = commandBody.substring(0, commandBody.indexOf("/at") - 1);
        String date = commandBody.substring(commandBody.indexOf("/at") + "/at ".length());
        return new Event(description, date);
    }

    /**
     * Finds the list of tasks that
     * contain the keyword from the user and returns it
     * @param keyword command body from user
     * @param taskList current tasklist
     * @return list of tasks containing user's search keyword
     * @throws DukeException if no task in the list contains the user's search keyword
     */
    private static List<Task> getTaskListWithKeyword(String keyword, TaskList taskList) throws DukeException {
        List<Task> taskListWithKeyword = new ArrayList<>();

        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).toString().contains(keyword)) {
                taskListWithKeyword.add(taskList.getTask(i));
            }
        }

        if (taskListWithKeyword.size() == 0) {
            throw new DukeException("OOPS!!! No such task with keyword is found.");
        }

        return taskListWithKeyword;
    }
}
