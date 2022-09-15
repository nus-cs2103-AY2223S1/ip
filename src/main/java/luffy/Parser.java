package luffy;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
     * Marks specified Task as completed.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after marking Task as completed
     */
    public String markTask(String s, TaskList tasks) {
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
    }

    /**
     * Marks specified Task as uncompleted.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after marking Task as uncompleted
     */
    public String unmarkTask(String s, TaskList tasks) {
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
    }

    /**
     * Creates Todo Task and returns String response.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after creating Todo Task
     */
    public String createTodo(String s, TaskList tasks) {
        try {
            Task newTask = new Todo(s.substring(5));
            tasks.add(newTask);
            return ui.returnTaskListStatus(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            return ERROR_PREFIX + "The description of a todo cannot be empty.";
        }
    }

    /**
     * Creates Deadline Task and returns String response.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after creating Deadline Task
     */
    public String createDeadline(String s, TaskList tasks) {
        try {
            String[] splitString = s.split(" /by ");
            LocalDate deadlineDate = LocalDate.parse(splitString[1]);
            Task newTask = new Deadline(splitString[0].substring(9), deadlineDate);
            tasks.add(newTask);
            return ui.returnTaskListStatus(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            return ERROR_PREFIX + "The description of a deadline cannot be empty.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return ERROR_PREFIX + "The date of a deadline cannot be empty.";
        } catch (DateTimeParseException e) {
            return ERROR_PREFIX + "The date of a deadline must be in format yyyy-mm-dd.";
        }
    }

    /**
     * Creates Event Task and returns String response.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after creating Event Task
     */
    public String createEvent(String s, TaskList tasks) {
        try {
            String[] splitString = s.split(" /at ");
            LocalDate eventDate = LocalDate.parse(splitString[1]);
            Task newTask = new Event(splitString[0].substring(6), eventDate);
            tasks.add(newTask);
            return ui.returnTaskListStatus(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            return ERROR_PREFIX + "The description of a event cannot be empty.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return ERROR_PREFIX + "The period of an event cannot be empty.";
        } catch (DateTimeParseException e) {
            return ERROR_PREFIX + "The date of a deadline must be in format yyyy-mm-dd.";
        }
    }

    /**
     * Deletes specified Task and returns String response.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after deleting Task
     */
    public String deleteTask(String s, TaskList tasks) {
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
    }

    /**
     * Finds queried Tasks and returns String response.
     *
     * @param s Full command String
     * @param tasks TaskList
     * @return Response after querying Tasks
     */
    public String findTask(String s, TaskList tasks) {
        try {
            String query = s.substring(5);
            return "Here are the matching tasks in your list:\n" + tasks.getQueriedTaskList(query).toString();
        } catch (StringIndexOutOfBoundsException e) {
            return ERROR_PREFIX + "Find query cannot be empty.";
        }
    }

    public String listDeadlines(String s, TaskList tasks) {
        ArrayList<Deadline> deadlines = tasks.getDeadlines();
        String responseString = "";
        if (deadlines.size() > 0) {
            Collections.sort(deadlines, Comparator.comparing(Deadline::getBy));
            for (int i = 0; i < deadlines.size(); i++) {
                responseString += (deadlines.get(i) + "\n");
            }
            return responseString;
        } else {
            return "There are no current Deadlines!";
        }
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
            return tasks.getSize() <= 0 ? "You have no tasks!" : tasks.toString();
        } else if (s.length() >= 6 && s.substring(0, 4).equals("mark")) {
            return markTask(s, tasks);
        } else if (s.length() >= 8 && s.substring(0, 6).equals("unmark")) {
            return unmarkTask(s, tasks);
        } else if (s.length() >= 4 && s.substring(0, 4).equals("todo")) {
            return createTodo(s, tasks);
        } else if (s.length() >= 9 && s.substring(0, 9).equals("deadlines")) {
            return listDeadlines(s, tasks);
        } else if (s.length() >= 8 && s.substring(0, 8).equals("deadline")) {
            return createDeadline(s, tasks);
        } else if (s.length() >= 5 && s.substring(0, 5).equals("event")) {
            return createEvent(s, tasks);
        } else if (s.length() >= 6 && s.substring(0, 6).equals("delete")) {
            return deleteTask(s, tasks);
        } else if (s.length() >= 5 && s.substring(0, 4).equals("find")) {
            return findTask(s, tasks);
        } else {
            return ERROR_PREFIX + "I'm sorry, but I don't know what that means :-(";
        }
    }
}
