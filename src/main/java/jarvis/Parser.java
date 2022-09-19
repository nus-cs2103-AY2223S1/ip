package jarvis;

import jarvis.exception.JarvisException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.task.ToDo;

/**
 * Parser processes commands given by user and returns a response by the Jarvis.
 */
public class Parser {
    private static final String INTRODUCTION = "Hello. I am Jarvis \n"
            + "What can I do for you today?";
    private static final String FAREWELL = "Goodbye, have a good day.";
    private TaskList tasks;

    /**
     * Returns a new Parser object with the given list of tasks.
     * @param tasks Current list of tasks.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads and executes commands given by user.
     * @throws JarvisException if command entered is not recognised.
     */
    public String readCommand(String input) throws JarvisException {
        String output = "";

        //check if userinput is bye, break if true
        if (equalsBye(input)) {
            return FAREWELL;
        }
        //if userinput equals list, return task list
        if (equalsList(input)) {
            return handleList(tasks);
        }
        // if userInput equals find, find tasks which match given string
        if (equalsFind(input)) {
            return handleFind(tasks, input);
        }

        //if userinput equals mark, check which task and mark it
        if (equalsMark(input)) {
            return handleMark(tasks, input);
        }
        //if userinput equals unmark, check which task and unmark
        if (equalsUnmark(input)) {
            return handleUnmark(tasks, input);
        }
        //if userinput equals delete, check which task and delete
        if (equalsDelete(input)) {
            return handleDelete(tasks, input);
        }

        //if userinput equals to do add new to do task to list
        if (equalsToDo(input)) {
            return handleToDo(tasks, input);
        }

        //if userinput equals deadline add new deadline task to list
        if (equalsDeadline(input)) {
            return handleDeadline(tasks, input);
        }
        //if userinput equals event add new event task to list
        if (equalsEvent(input)) {
            return handleEvent(tasks, input);
        } else {
            return "I'm sorry, but I don't know what that means";
        }
    }

    public static String introduction() {
        return INTRODUCTION;
    }

    private static String handleList(TaskList tasks) {
        String output = ("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getList().size(); i++) {
            if (tasks.getList().get(i) == null) {
                break;
            }
            output = output + (i + 1) + ". " + tasks.getList().get(i).toString() + "\n";
        }
        return output;
    }

    private static String handleFind(TaskList tasks, String input) {
        String keyword = input.substring((5));
        String output = ("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task currTask = tasks.getList().get(i);
            if (currTask == null) {
                return output;
            }
            if (currTask.toString().contains(keyword)) {
                output = output + ((i + 1) + ". " + currTask) + "\n";
            }
        }
        return output;
    }

    private static String handleMark(TaskList tasks, String input) {
        int toMark = Integer.parseInt(input.substring(5)) - 1;
        tasks.getList().get(toMark).mark();
        String output = "Great. I have marked this task as done:\n " + tasks.getList().get(toMark).toString();
        return output;
    }

    private static String handleUnmark(TaskList tasks, String input) {
        int toMark = Integer.parseInt(input.substring(7)) - 1;
        tasks.getList().get(toMark).unmark();
        String markResponse = "Ok, I have marked this task as not done yet:\n ";
        String output = markResponse + tasks.getList().get(toMark).toString();
        return output;
    }

    private static String handleDelete(TaskList tasks, String input) {
        int toDelete = Integer.parseInt(input.substring(7)) - 1;
        Task deleteTask = tasks.getList().get(toDelete);
        tasks.getList().remove(toDelete);
        String deleteResponse = "Noted. I have removed this task:\n ";
        String output = deleteResponse + deleteTask.toString();
        return output;
    }

    private static String handleToDo(TaskList tasks, String input) throws JarvisException {
        String description = input.substring(4);
        if (description.equals("")) {
            throw new JarvisException("The description of a todo cannot be empty");
        }
        tasks.getList().add(new ToDo(description));
        String output = ("Got it. I've added this task:\n " + tasks.getList().get(Task.getCount() - 1)
                + "\nNow you have " + (Task.getCount()) + " tasks in the list.");
        return output;
    }

    private static String handleDeadline(TaskList tasks, String input) {
        int divisor = input.indexOf("/by");
        String description = input.substring(9, divisor - 1);
        String date = input.substring(divisor + 4);
        tasks.getList().add(new Deadline(description, date));
        String output = ("Got it. I've added this task:\n " + tasks.getList().get(Task.getCount() - 1)
                + "\nNow you have " + (Task.getCount()) + " tasks in the list.");
        return output;
    }

    private static String handleEvent(TaskList tasks, String input) {
        int divisor = input.indexOf("/at");
        String description = input.substring(6, divisor - 1);
        String date = input.substring(divisor + 4);
        tasks.getList().add(new Event(description, date));
        String output = ("Got it. I've added this task:\n " + tasks.getList().get(Task.getCount() - 1)
                + "\nNow you have " + (Task.getCount()) + " tasks in the list.");
        return output;
    }

    private static boolean equalsBye(String input) {
        return input.equals("bye");
    }

    private static boolean equalsList(String input) {
        return input.equals("list");
    }
    private static boolean equalsFind(String input) {
        return input.length() > 4 && input.substring(0,4).equals("find");
    }

    private static boolean equalsMark(String input) {
        return input.length() > 4 && input.substring(0,4).equals("mark");
    }

    private static boolean equalsUnmark(String input) {
        return input.length() > 6 && input.substring(0,6).equals("unmark");
    }

    private static boolean equalsDelete(String input) {
        return input.length() > 6 && input.substring(0,6).equals("delete");
    }

    private static boolean equalsToDo(String input) {
        return input.length() > 3 && input.substring(0,4).equals("todo");
    }

    private static boolean equalsDeadline(String input) {
        return input.length() > 8 && input.substring(0,8).equals("deadline");
    }

    private static boolean equalsEvent(String input) {
        return input.length() > 7 && input.substring(0,5).equals("event");
    }
}
