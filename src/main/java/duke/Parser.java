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
     * This method is used to convert the date in YYYY-MM-DD format to MMM d YYYY
     * (e.g 2019-10-02 to OCT 2 2019)
     * @param date This is string of date in YYYY-MM-DD format
     * @return String This returns the string of the date in the MMM d YYYY format
     */
    public static String parseDate(String date) throws DateTimeParseException {
        LocalDate d = LocalDate.parse(date);
        String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    /**
     * Convert the user's raw string input into commands like
     * quit, list out tasks, adding/removing/marking/finding specific tasks. When
     * an invalid input is received, an error is thrown and user can try another
     * input
     * @param rawInput This is the user's string input
     * @param taskList This is the user's current list of tasks
     * @param ui This is for the Duke program to interact/print outputs to the user
     */
    public static String parseCommand(String rawInput, TaskList taskList, Ui ui) {
        DukeException invalidCommandException = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

        try {
            String trimmedInput = rawInput.replaceAll("\\s{2,}", " ").trim();
            String[] splitStr = trimmedInput.split(" ");
            String commandType = splitStr[0];

            if (splitStr.length == 1) {
                switch (commandType) {
                case "bye":
                return ui.quit();
                case "list":
                return ui.listOutTasks(taskList);
                default:
                throw invalidCommandException;
                }
            }

            String commandBody = trimmedInput.substring(commandType.length() + 1);
            int commandBodyWordCount = commandBody.split(" ").length;
            switch (commandType) {
                case "mark":
                case "unmark":
                Task taskToMark = getTaskToMark(commandBody, commandBodyWordCount, taskList);
                return commandType.equals("mark") ? ui.markTask(taskToMark) : ui.unmarkTask(taskToMark);
                case "delete":
                Task taskToDelete = getTaskToDelete(commandBody,commandBodyWordCount,taskList);
                return ui.deleteTask(taskList, taskToDelete);
                case "find":
                List<Task> taskListWithKeyWord = getAllTaskWithKeyword(commandBody, taskList);
                return ui.printTasksWithKeyword(taskListWithKeyWord);
                case "todo":
                Task toDoToAdd = getToDoToAdd(commandBody,commandBodyWordCount);
                return ui.addTask(taskList,toDoToAdd);
                case "deadline":
                Task deadlineToAdd = getDeadlineToAdd(commandBody,commandBodyWordCount);
                return ui.addTask(taskList,deadlineToAdd);
                case "event":
                Task eventToAdd = getEventToAdd(commandBody,commandBodyWordCount);
                return ui.addTask(taskList, eventToAdd);
                default:
                throw invalidCommandException;
            }

        } catch (DukeException de) {
            return de.getMessage();
        } catch (DateTimeParseException dtpe) {
            return "Deadline date is entered incorrectly. Enter date in YYYY-MM-DD format (e.g 2019-10-02)";
        } catch (NumberFormatException nfe) {
            return "Invalid task number";
        }
    }

    /**
     * Checks if the user's chosen task index number is valid
     * @param index The user's chosen task index number
     * @param taskList The current tasklist
     * @return Returns true when is valid
     * @throws DukeException Throw DukeException when not valid
     */
    private static boolean isValidIndex(int index, TaskList taskList) throws DukeException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new DukeException("OOPS!!! Index " + index + " is not valid. Please enter a task number from 1 - " + taskList.getSize());
        }
        return true;
    }

    /**
     * Deletes the task from the tasklist
     * @param commandBody User's string input without the starting command word split with " " into an array
     * @param commandBodyWordCount Number of words in command body separated by " "
     * @param taskList User's list of tasks
     * @return Task The task from the user's task list to be deleted
     * @throws DukeException This tells user to enter a valid number when an invalid task number is entered
     */
    private static Task getTaskToDelete(String commandBody, int commandBodyWordCount, TaskList taskList) throws NumberFormatException, DukeException {
        int index = Integer.parseInt(commandBody);
        if (commandBodyWordCount != 1 || !isValidIndex(index, taskList)) {
            return null;
        }
        return taskList.getTask(index - 1);
    }

    /**
     * Marks the task from taskList to be done/undone
     * @param commandBody User's string input without the starting command word split with " " into an array
     * @param commandBodyWordCount Number of words in command body separated by " "
     * @param taskList User's list of tasks
     * @return Task The task from the user's task list to be marked/unmarked
     * @throws DukeException Tells user to enter a valid number when an invalid task number is entered
     */
    private static Task getTaskToMark(String commandBody, int commandBodyWordCount, TaskList taskList) throws NumberFormatException, DukeException {
        int index = Integer.parseInt(commandBody);
        if (commandBodyWordCount != 1 || !isValidIndex(index,taskList)) {
            return null;
        }
        return taskList.getTask(index - 1);
    }

    /**
     * Creates a ToDo task to be added with
     * description from the command body
     * Retruns ToDo if description is in command body
     * @param commandBody The command of the user without the starting command type
     * @param commandBodyWordCount The number of words in the command body
     * @return ToDo task with description from command body
     * @throws DukeException Throws DukeException if command body is empty
     */
    private static Task getToDoToAdd(String commandBody, int commandBodyWordCount) throws DukeException {
        if (commandBodyWordCount < 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        return new ToDo(commandBody);
    }

    /**
     * Creates a Deadline task to be added with description
     * and date from the command body
     * @param commandBody The command of the user without the starting command type
     * @param commandBodyWordCount The number of words in the command body
     * @return Deadline task with description and date from command body
     * @throws DukeException
     */
    private static Task getDeadlineToAdd(String commandBody, int commandBodyWordCount) throws DukeException {
        if (commandBodyWordCount < 3) {
            throw new DukeException("OOPS!!! Add an deadline in the following format: \n\ndeadline <task description> /by <YYYY-MM-DD>\n");
        }
        if (commandBody.indexOf(" /by") - 1 < 0) {
            throw new DukeException("OOPS!!! Please set date of deadline with /by.\n");
        }
        String description = commandBody.substring(0,commandBody.indexOf("/by") - 1);
        String date = commandBody.substring(commandBody.indexOf("/by") + 4);
        String formattedDate = Parser.parseDate(date);
        return new Deadline(description, formattedDate);
    }

    /**
     * Creates a Event task to be added with description
     * and date from the command body
     * @param commandBody The command of the user without the starting command type
     * @param commandBodyWordCount The number of words in the command body
     * @return Event task with description and date from command body
     * @throws DukeException
     */
    private static Task getEventToAdd (String commandBody, int commandBodyWordCount) throws DukeException {
        if (commandBodyWordCount < 3) {
            throw new DukeException("OOPS!!! Add an event in the following format: \n\nevent <task description> /at <date>\n");
        }
        if (commandBody.indexOf(" /at") - 1 < 0) {
            throw new DukeException("OOPS!!! Please set date of at with /at.\n");
        }
        String description = commandBody.substring(0,commandBody.indexOf("/at") - 1);
        String date = commandBody.substring(commandBody.indexOf("/at") + 4);
        return new Event(description, date);
    }

    /**
     * Finds the list of tasks that
     * contain the keyword from the user
     * @param keyword Command body from user
     * @param taskList User's complete task list
     * @return list of tasks containing user's search keyword
     * @throws DukeException This tells the user if no such task contains the user's search keyword
     */
    private static List<Task> getAllTaskWithKeyword(String keyword, TaskList taskList) throws DukeException {
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
