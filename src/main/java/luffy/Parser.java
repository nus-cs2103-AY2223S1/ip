package luffy;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Parser class to handle user inputs.
 *
 * @author Silas Tay (A0233425M)
 */
public class Parser {
    private Ui ui;
    private static final String ERROR_PREFIX = "☹ OOPS!!! ";

    /**
     * Constructor for Parser class.
     */
    public Parser() {
        this.ui = new Ui();
    }

    /**
     * Parses through user inputs and returns appropriate response.
     *
     * @param s User input line
     * @param tasks Tasklist of Luffy instance
     * @return String response of Luffy
     */
    public String parse(String s, TaskList tasks) {
        if (s.equals("list")) {
            return tasks.toString();
        } else if (s.length() >= 6 && s.substring(0, 4).equals("mark")) {
            try {
                int taskIndex = Integer.parseInt(s.substring(5, 6)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                    tasks.markCompleted(taskIndex);
                    return "Marked task " +  (taskIndex + 1) + " as completed: \n" + tasks.getTask(taskIndex);
                } else {
                    return ERROR_PREFIX + "Task index out of bounds!";
                }
            } catch (StringIndexOutOfBoundsException e) {
                return ERROR_PREFIX + "Task index cannot be empty!";
            }
        } else if (s.length() >= 8 && s.substring(0, 6).equals("unmark")) {
            try {
                int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                    tasks.markUncompleted(taskIndex);
                    return "Marked task " +  (taskIndex + 1) + " as uncompleted: \n" + tasks.getTask(taskIndex);
                } else {
                    return ERROR_PREFIX + "Task index out of bounds!";
                }
            } catch (StringIndexOutOfBoundsException e) {
                return ERROR_PREFIX + "Task index cannot be empty!";
            }
        } else {
            Task newTask;
            if (s.length() >= 4 && s.substring(0, 4).equals("todo")) {
                try {
                    newTask = new Todo(s.substring(5));
                    tasks.add(newTask);
                    return ui.returnTaskListStatus(tasks);
                } catch(StringIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "The description of a todo cannot be empty.";
                }
            } else if (s.length() >= 8 && s.substring(0, 8).equals("deadline")){
                try {
                    String[] splitString = s.split(" /by ");
                    LocalDate deadlineDate = LocalDate.parse(splitString[1]);
                    newTask = new Deadline(splitString[0].substring(9), deadlineDate.toString());
                    tasks.add(newTask);
                    return ui.returnTaskListStatus(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "The description of a deadline cannot be empty.";
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "The date of a deadline cannot be empty.";
                } catch (DateTimeParseException e) {
                    return ERROR_PREFIX + "The date of a deadline must be in format yyyy-mm-dd.";
                }
            } else if (s.length() >= 5 && s.substring(0, 5).equals("event")) {
                try {
                    String[] splitString = s.split(" /at ");
                    LocalDate eventDate = LocalDate.parse(splitString[1]);
                    newTask = new Event(splitString[0].substring(6), eventDate.toString());
                    tasks.add(newTask);
                    return ui.returnTaskListStatus(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "The description of a event cannot be empty.";
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "The period of an event cannot be empty.";
                } catch (DateTimeParseException e) {
                    return ERROR_PREFIX + "The date of a deadline must be in format yyyy-mm-dd.";
                }
            } else if (s.length() >= 6 && s.substring(0, 6).equals("delete")) {
                try {
                    int taskIndex = Integer.parseInt(s.substring(7, 8)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                        tasks.delete(taskIndex);
                        return ui.returnTaskListStatus(tasks);
                    } else {
                        return ERROR_PREFIX + "Task index " + (taskIndex + 1) + " is not valid!";
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "Task index cannot be empty.";
                }
            }else if (s.length() >= 5 && s.substring(0, 4).equals("find")) {
                try {
                    String query = s.substring(5);
                    return "Here are the matching tasks in your list:\n" + tasks.getQueriedTaskList(query).toString();
                } catch (StringIndexOutOfBoundsException e) {
                    return ERROR_PREFIX + "Find query cannot be empty.";
                }
            } else {
                return ERROR_PREFIX + "I'm sorry, but I don't know what that means :-(";
            }
        }
    }
}
