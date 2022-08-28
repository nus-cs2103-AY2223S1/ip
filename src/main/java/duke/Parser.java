package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public Parser() {}

    /**
     * This method is used to convert the date in YYYY-MM-DD format to MMM d YYYY
     * (e.g 2019-10-02 to OCT 2 2019)
     * @param date This is string of date in YYYY-MM-DD format
     * @return String This returns the string of the date in the MMM d YYYY format
     */
    public static String parseDate(String date) {
        LocalDate d = LocalDate.parse(date);
        String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    /**
     * This method is used to convert the user's string input into commands like
     * quit, list out tasks, adding/removing/marking/finding specific tasks. When
     * an invalid input is received, an error is thrown and user can try another
     * input
     * @param command This is the user's string input
     * @param taskList This is the user's current list of tasks
     * @param ui This is for the Duke program to interact/print outputs to the user
     */
    public static void parseCommand(String command, TaskList taskList, Ui ui) {
        try {
            if (command.equals("bye")) {
                ui.quit();
            } else if (command.equals("list")) {
                ui.listOutTasks(taskList);
            } else {
                String[] splitStr = command.split(" ");
                Task taskToMark = getTaskToMark(splitStr, taskList);
                Task taskToAdd = getTaskToAdd(command);
                Task taskToDelete = getTaskToDelete(splitStr, taskList);
                List<Task> taskListWithKeyWord = getAllTaskWithKeyword(command, taskList);
                if (taskToMark != null) {
                    String action = splitStr[0];
                    if (action.equals("mark")) {
                        ui.markTask(taskToMark);
                    } else {
                        ui.unmarkTask(taskToMark);
                    }
                } else if (taskToAdd != null) {
                    ui.addTask(taskList, taskToAdd);
                } else if (taskToDelete != null) {
                    ui.deleteTask(taskList, taskToDelete);
                } else if (taskListWithKeyWord.size() != 0) {
                    ui.printTasksWithKeyword(taskListWithKeyWord);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            }
        } catch (DukeException de) {
            System.out.println(de);
        }
    }

    /**
     * This method takes in the string input of the user
     * and return the task from the taskList to be deleted.
     * When an invalid number is entered, user will get to
     * enter again
     * @param splitStr This is the user's string input split with " " into an array
     * @param taskList This is the user's list of tasks
     * @return Task The task from the user's task list to be deleted
     * @throws DukeException This tells user to enter a valid number when an invalid task number is entered
     */
    private static Task getTaskToDelete(String[] splitStr, TaskList taskList) throws DukeException {
        try {
            if (splitStr.length != 2) {
                return null;
            }
            int index = Integer.parseInt(splitStr[1]);
            String action = splitStr[0];
            boolean validAction = action.equals("delete");
            boolean validIndex = index > 0 && index <= taskList.getSize();
            if (validIndex && validAction) {
                return taskList.getTask(index - 1);
            } else if (validAction && !validIndex) {
                throw new DukeException("☹ OOPS!!! Please enter a valid task number to delete");
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * This method takes in the string input of the user
     * and return the task from the taskList to be marked/unmarked.
     * When an invalid number is entered, user will get to
     * enter again
     * @param splitStr This is the user's string input split with " " into an array
     * @param taskList This is the user's list of tasks
     * @return Task The task from the user's task list to be marked/unmarked
     * @throws DukeException This tells user to enter a valid number when an invalid task number is entered
     */
    private static Task getTaskToMark(String[] splitStr, TaskList taskList) throws DukeException {
        try {
            if (splitStr.length != 2) {
                return null;
            }
            int index = Integer.parseInt(splitStr[1]);
            String action = splitStr[0];
            boolean validAction = action.equals("mark") || action.equals("unmark");
            boolean validIndex = index > 0 && index <= taskList.getSize();
            if (validIndex && validAction) {
                return taskList.getTask(index - 1);
            } else if (validAction && !validIndex) {
                throw new DukeException("☹ OOPS!!! Please enter a valid task number to mark/unmark");
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * This method takes in the string input of the user
     * and return the task from the taskList to be added.
     * @param str This is the user's string input
     * @return Task The task to be added to the user's task list
     * @throws DukeException This tells the user whether the task is missing a date or description
     */
    private static Task getTaskToAdd(String str) throws DukeException {
        String[] splitStr = str.split(" ");
        String type = splitStr[0];
        if (type.equals("todo")) {
            if (splitStr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            String description = str.substring(type.length() + 1);
            return new ToDo(description);
        } else if (type.equals("deadline")) {
            if (splitStr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
            }
            if (str.indexOf("/by") - 1 < 0) {
                throw new DukeException("☹ OOPS!!! Please set date of deadline with /by.\n");
            }
            String description = str.substring(type.length() + 1, str.indexOf("/by") - 1);
            String date = str.substring(str.indexOf("/by") + 4);
            String formattedDate = Parser.parseDate(date);
            return new Deadline(description, formattedDate);
        } else if (type.equals("event")) {
            if (splitStr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n");
            }
            if (str.indexOf("/at") - 1 < 0) {
                throw new DukeException("☹ OOPS!!! Please set date of event with /at.\n");
            }
            String description = str.substring(type.length() + 1, str.indexOf("/at") - 1);
            String date = str.substring(str.indexOf("/at") + 4);
            String formattedDate = Parser.parseDate(date);
            return new Event(description, formattedDate);
        } else {
            return null;
        }
    }

    /**
     * This method takes in the users string input
     * and returns a list of tasks that contains
     * the user's search keyword
     * @param str Command input from user
     * @param taskList User's complete task list
     * @return list of tasks containing user's search keyword
     * @throws DukeException This tells the user if no such task contains the user's search keyword
     */
    private static List<Task> getAllTaskWithKeyword(String str, TaskList taskList) throws DukeException {
        String[] splitStr = str.split(" ");
        List<Task> taskListWithKeyword = new ArrayList<>();
        if (splitStr[0].equals("find")) {
            String keyword = str.substring(5);
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.getTask(i).toString().contains(keyword)) {
                    taskListWithKeyword.add(taskList.getTask(i));
                }
            }
            if (taskListWithKeyword.size() == 0) {
                throw new DukeException("☹ OOPS!!! No such task with keyword is found.");
            }
        }
        return taskListWithKeyword;
    }
}
