package duke;

import javafx.application.Platform;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class that deals with making sense of the user command
 */
public class Parser {
    /**
     * Parses the input and returns if the scanner should scan a new line
     *
     * @param first The users input
     * @param lst The tasklist object
     * @param store The storage object
     * @return String Duke's Output
     */
    public static String parse(String first, TaskList lst, Storage store) {
        String output = "";
        try {
            if (first.equals("bye")) {
                Platform.exit();
            } else if (first.equals("list")) {
                output = listMessage(lst);
            } else if (first.length() == 6 && first.substring(0, 4).equals("mark")) {
                output = markMessage(first, lst, store);
            } else if (first.length() == 8 && first.substring(0, 6).equals("unmark")) {
                output = unmarkMessage(first, lst, store);
            } else if (first.length() >= 4 && first.substring(0, 4).equals("todo")) {
                output = todoMessage(first, lst, store);
            } else if (first.length() >= 8 && first.substring(0, 8).equals("deadline")) {
                output = deadlineMessage(first, lst, store);
            } else if (first.length() >= 5 && first.substring(0, 5).equals("event")) {
                output = eventMessage(first, lst, store);
            } else if (first.length() == 8 && first.substring(0, 6).equals("delete")) {
                output = deleteMessage(first, lst, store);
            } else if (first.length() >= 4 && first.substring(0, 4).equals("find")) {
                output = findMessage(first, lst);
            }
            else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            return ex.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Prints the list when user types "list"
     *
     * @param lst The tasklist
     * @return String The output of Duke when list is typed
     */
    public static String listMessage(TaskList lst) throws DukeException {
        String list = "";
        if (lst.count() == 0) {
            throw new DukeException("Your list is empty!!");
        } else {
            System.out.println("try");
            for (int i = 1; i < lst.count() + 1; i++) {
                list += (i) + ". " + lst.getTask(i - 1) + "\n";
            }
        }
        return list;
    }

    /**
     * Marks a task as completed
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     * @return String The output of Duke when the user marks a task
     */
    public static String markMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        char index = str.charAt(5);
        int number = Integer.parseInt(String.valueOf(index));
        if (number > lst.count()) {
            throw new DukeException("No such task.");
        }
        String reply = lst.markTask(number - 1);
        storage.updateFile(lst);
        return reply;
    }

    /**
     * Marks a task as incomplete
     *
     * @param str The users input
     * @param lst The tasklist
     * @return String The output of Duke when the user unmarks a task
     */
    public static String unmarkMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        char index = str.charAt(7);
        int number = Integer.parseInt(String.valueOf(index));
        if(number > lst.count()) {
            throw new DukeException("No such task.");
        }
        String reply = lst.unmarkTask(number - 1);
        storage.updateFile(lst);
        return reply;
    }

    /**
     * Adds a Todo to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     * @return String The output of Duke when the user adds a Todo task
     */
    public static String todoMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if(str.length() > 5) {
            String description = str.substring(5);
            Todo d = new Todo(description);
            lst.addTask(d);
            storage.updateFile(lst);
            return "Got it. I've added this task:\n " + d +
                    "\nNow you have " + lst.count() + " tasks in the list.";
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a Deadline to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     * @return String The output of Duke when the user adds a Deadline task
     */
    public static String deadlineMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if(str.length() > 9) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(9, sepPos);
                String by = str.substring(sepPos + 4);
                DateTimeFormatter fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate.parse(by, fromFormat);
                } catch (DateTimeParseException e) {
                    return "Deadline format has to be in yyyy-MM-dd";
                }
                Deadline l = new Deadline(description, LocalDate.parse(by));
                lst.addTask(l);
                storage.updateFile(lst);
                return "Got it. I've added this task:\n " + l +
                        "\nNow you have " + lst.count() + " tasks in the list.";
            } else {
                throw new DukeException("You have to include the deadline");
            }
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds a Event to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     * @return String The output of Duke when the user adds a Event task
     */
    public static String eventMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if (str.length() > 6) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(6, sepPos);
                String at = str.substring(sepPos + 4);
                Event e = new Event(description, at);
                lst.addTask(e);
                storage.updateFile(lst);
                return "Got it. I've added this task:\n " + e +
                        "\nNow you have " + lst.count() + " tasks in the list.";
            } else {
                throw new DukeException("You have to include the timings of an event");
            }
        } else {
            throw new DukeException("The description of a event cannot be empty.");
        }
    }

    /**
     * Deletes a task from the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     * @return String The output of Duke when the user deletes a task
     */
    public static String deleteMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if (str.length() > 7) {
            char index = str.charAt(7);
            int number = Integer.parseInt(String.valueOf(index));
            if (number > lst.count()) {
                throw new DukeException("No such task.");
            }
            Task t = lst.getTask(number - 1);
            lst.deleteTask(t);
            storage.updateFile(lst);
            return "Noted. I've removed this task: \n " + t + "\nNow you have "
                    + lst.count() + " tasks in the list.";
        }
        else {
            throw new DukeException("You have to enter the task index that you want to delete");
        }
    }

    /**
     * Finds a task based on a keyword
     *
     * @param str The users input
     * @param lst The tasklist
     * @return String The output of Duke when the user finds a task
     */
    public static String findMessage(String str, TaskList lst) throws DukeException, IOException {
        if (str.length() > 6) {
            String keyword = str.substring(5);
            ArrayList<Task> allTasks = lst.findTask(keyword);
            int counter = 1;
            if (allTasks.isEmpty()) {
                throw new DukeException("No such tasks with that keyword!!");
            } else {
                String tasks = "Here are the matching tasks in your list:\n";
                for (Task t: allTasks) {
                    tasks += counter + ". " + t.toString() + "\n";
                    counter++;
                }
                return tasks;
            }
        } else {
            throw new DukeException("Add keyword to find");
        }
    }
}
