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
                output = getList(lst);
            } else if (first.length() == 6 && first.substring(0, 4).equals("mark")) {
                output = markTask(first, lst, store);
            } else if (first.length() == 8 && first.substring(0, 6).equals("unmark")) {
                output = unmarkTask(first, lst, store);
            } else if (first.length() >= 4 && first.substring(0, 4).equals("todo")) {
                output = getTodo(first, lst, store);
            } else if (first.length() >= 8 && first.substring(0, 8).equals("deadline")) {
                output = getDeadline(first, lst, store);
            } else if (first.length() >= 5 && first.substring(0, 5).equals("event")) {
                output = getEvent(first, lst, store);
            } else if (first.length() == 8 && first.substring(0, 6).equals("delete")) {
                output = deleteTask(first, lst, store);
            } else if (first.length() >= 4 && first.substring(0, 4).equals("find")) {
                output = findTask(first, lst);
            } else if (first.length() >= 8 && first.substring(0, 8).equals("postpone")) {
                output = postponeTask(first, lst, store);
            } else {
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
    public static String getList(TaskList lst) throws DukeException {
        String list;
        if (lst.count() == 0) {
            throw new DukeException("Your list is empty!!");
        } else {
            list = Ui.getListMessage(lst);
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
    public static String markTask(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        char index = str.charAt(5);
        int number = Integer.parseInt(String.valueOf(index));
        if (number > lst.count()) {
            throw new DukeException("No such task.");
        }
        Task t = lst.markTask(number - 1);
        storage.updateFile(lst);
        return Ui.getMarkMessage(t);
    }

    /**
     * Marks a task as incomplete
     *
     * @param str The users input
     * @param lst The tasklist
     * @return String The output of Duke when the user unmarks a task
     */
    public static String unmarkTask(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        char index = str.charAt(7);
        int number = Integer.parseInt(String.valueOf(index));
        if(number > lst.count()) {
            throw new DukeException("No such task.");
        }
        Task t = lst.unmarkTask(number - 1);
        storage.updateFile(lst);
        return Ui.getUnmarkMessage(t);
    }

    /**
     * Adds a Todo to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     * @return String The output of Duke when the user adds a Todo task
     */
    public static String getTodo(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if(str.length() > 5) {
            String description = str.substring(5);
            Todo d = new Todo(description);
            lst.addTask(d);
            storage.updateFile(lst);
            return Ui.todoMessage(d, lst.count());
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
    public static String getDeadline(String str, TaskList lst, Storage storage) throws DukeException, IOException {
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
                return Ui.deadlineMessage(l, lst.count());
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
    public static String getEvent(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if (str.length() > 6) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(6, sepPos);
                String at = str.substring(sepPos + 4);
                DateTimeFormatter fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate.parse(at, fromFormat);
                } catch (DateTimeParseException e) {
                    return "Event timing has to be in the format yyyy-MM-dd";
                }
                Event e = new Event(description, LocalDate.parse(at));
                lst.addTask(e);
                storage.updateFile(lst);
                return Ui.eventMessage(e, lst.count());
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
    public static String deleteTask(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if (str.length() > 7) {
            char index = str.charAt(7);
            int number = Integer.parseInt(String.valueOf(index));
            if (number > lst.count()) {
                throw new DukeException("No such task.");
            }
            Task t = lst.getTask(number - 1);
            lst.deleteTask(t);
            storage.updateFile(lst);
            return Ui.deleteMessage(t, lst.count());
        } else {
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
    public static String findTask(String str, TaskList lst) throws DukeException, IOException {
        if (str.length() > 6) {
            String keyword = str.substring(5);
            ArrayList<Task> allTasks = lst.filterTask(keyword);
            if (allTasks.isEmpty()) {
                throw new DukeException("No such tasks with that keyword!!");
            } else {
                return Ui.findMessage(allTasks);
            }
        } else {
            throw new DukeException("Add keyword to find");
        }
    }

    /**
     * Postpone a task
     *
     * @param str The users input
     * @param lst The tasklist
     * @param store The storage
     * @return String The output of Duke when the user postpones a Task
     */
    public static String postponeTask(String str, TaskList lst, Storage store) throws DukeException, IOException {
        String[] stringDetails = str.split(" ");
        if (stringDetails.length != 3) {
            throw new DukeException("You are missing a field.");
        }
        String number = stringDetails[1];
        int index = Integer.parseInt(String.valueOf(number));
        if (index > lst.count()) {
            throw new DukeException("No such task.");
        }
        String newDate = stringDetails[2];
        DateTimeFormatter fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(newDate, fromFormat);
        } catch (DateTimeParseException e) {
            return "New timing has to be in the format yyyy-MM-dd";
        }
        Task t = lst.getTask(index - 1);
        if (t instanceof Event) {
            Event e = (Event) t;
            e.changeDate(LocalDate.parse(newDate));
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            d.changeDate(LocalDate.parse(newDate));
        } else {
            return "Task is a Todo and has no date to postpone";
        }
        store.updateFile(lst);
        return Ui.postponeMessage(t);
    }



}
