package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * the object that stores the list of tasks
 */
public class TaskList {
    /** list that stores the tasks*/
    private ArrayList<Task> list;

    /** storage object */
    private Storage storage;

    /** count to keep track of items */
    private int count;

    /**
     * Initializes tasklist object
     * @param storage storage object used to store tasks
     */
    public TaskList(Storage storage) {
        this.list = storage.getList();
        this.storage = storage;
        this.count = storage.getCount();
    }

    /**
     * prints the list of tasks
     */
    public String printTaskList() {
        String string = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            string += Integer.toString(i + 1) + ". " + list.get(i).getTask() + "\n";
        }
        return string;
    }

    /**
     *  Prints tasks that match the filter
     * @param filter the string used to filter the tasks
     */
    public String printFilteredTaskList(String filter) {
        String string = "";
        string += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            if (list.get(i).getItem().contains(filter)) {
                string += Integer.toString(i + 1) + ". " + list.get(i).getTask() + "\n";
            }
        }
        return string;
    }

    /**
     * sets the task to done
     * @param index Index of the task in the list
     */
    public String setDone(int index) {
        try {
            String string = "";
            list.get(index).setDone();
            string += "Nice! I've marked this task as done:\n";
            string += list.get(index).getTask() + "\n";
            storage.writeToFile();
            return string;
        } catch (NullPointerException e) {
            return ("Oops! Looks like the task number is incorrect :(");
        }
    }

    /**
     * Sets the task to not done
     * @param index Index of the task in the list
     */
    public String setUndone(int index) {
        try {
            String string = "";
            list.get(index).setNotDone();
            string += "OK, I've marked this task as not done yet:\n";
            string += list.get(index).getTask();
            storage.writeToFile();
            return string;
        } catch (NullPointerException e) {
            return ("Oops! Looks like the task number is incorrect :(");
        }
    }

    /**
     * Creates a deadline object and stores it
     * @param item the input of the user
     */
    public String createDeadline(String item) {
        try {
            String string = "";
            int slash = 0;
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) == '/') {
                    slash = i;
                    break;
                }
            }
            LocalDate date = LocalDate.parse(item.substring(slash + 4));
            list.add(new Deadline(item.substring(9, slash - 1), date));
            string += "Got it. I've added this task:\n";
            string += list.get(list.size() - 1).getTask() + "\n";
            storage.writeToFile();
            count = count + 1;
            string += "Now you have " + Integer.toString(count) + " tasks in the list";
            return string;
        } catch (IndexOutOfBoundsException e) {
            return ("☹ OOPS!!! The description of a deadline has to be in the format"
                    + " deadline <task> /by <date and time>");
        } catch (DateTimeParseException e) {
            return ("OOPS!! Format of the date is wrong");
        }
    }

    /**
     * Creates and event object and stores it
     * @param item the user's input
     */
    public String createEvent(String item) {
        try {
            String string = "";
            int slash = 0;
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) == '/') {
                    slash = i;
                    break;
                }
            }
            list.add(new Event(item.substring(6, slash - 1), item.substring(slash + 4)));
            string += "Got it. I've added this task:\n";
            string += list.get(list.size() - 1).getTask() + "\n";
            storage.writeToFile();
            count = count + 1;
            string += "Now you have " + Integer.toString(count) + " tasks in the list\n";
            return string;
        } catch (IndexOutOfBoundsException e) {
            return ("☹ OOPS!!! The description of a event has to be in the format event"
                    + " <task> /at <date and time>");
        }
    }

    /**
     * Creates a todo object and stores it
     * @param item The user's input
     */
    public String createTask(String item) {
        try {
            String string = "";
            list.add(new Todo(item.substring(5)));
            string += "Got it. I've added this task:\n";
            string += list.get(list.size() - 1).getTask() + "\n";
            storage.writeToFile();
            count = count + 1;
            string += "Now you have " + Integer.toString(count) + " tasks in the list";
            return string;
        } catch (IndexOutOfBoundsException e) {
            return ("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Deletes a task from the list
     * @param index Index of the task
     */
    public String deleteTask(int index) {
        try {
            String string = "";
            String text = list.get(index).getTask();
            list.remove(index);
            string += "Noted. I've removed this task:\n";
            string += text + "\n";
            storage.writeToFile();
            count = count - 1;
            string += "Now you have " + Integer.toString(count) + " tasks in the list";
            return string;
        } catch (IndexOutOfBoundsException e) {
            return ("Oops! Looks like the task number is incorrect :(");
        }
    }

}

