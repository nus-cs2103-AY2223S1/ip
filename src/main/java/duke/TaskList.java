package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

    public TaskList(Storage storage) {
        this.list = storage.getList();
        this.storage = storage;
        this.count = storage.getCount();
    }

    /**
     * prints the list of tasks
     */
    public void printTaskList() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            System.out.println(Integer.toString(i + 1) + ". " + list.get(i).getTask());
        }
    }

    public void printFilteredTaskList(String filter) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            if(list.get(i).getItem().contains(filter)) {
                System.out.println(Integer.toString(i + 1) + ". " + list.get(i).getTask());
            }
        }
    }

    /**
     * sets the task to done
     * @param index Index of the task in the list
     */
    public void setDone(int index) {
        try {
            list.get(index).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index).getTask());
            storage.writeToFile();
        } catch (NullPointerException e) {
            System.out.println("Oops! Looks like the task number is incorrect :(");
        }
    }

    /**
     * Sets the task to not done
     * @param index Index of the task in the list
     */
    public void setUndone(int index) {
        try {
            list.get(index).setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index).getTask());
            storage.writeToFile();
        } catch (NullPointerException e) {
            System.out.println("Oops! Looks like the task number is incorrect :(");
        }
    }

    /**
     * Creates a deadline object and stores it
     * @param item the input of the user
     */
    public void createDeadline(String item) {
        try {
            int slash = 0;
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) == '/') {
                    slash = i;
                    break;
                }
            }
            LocalDate date = LocalDate.parse(item.substring(slash + 4));
            list.add(new Deadline(item.substring(9, slash - 1), date));
            System.out.println("Got it. I've added this task:");
            System.out.println(list.get(list.size() - 1).getTask());
            storage.writeToFile();
            count = count + 1;
            System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a deadline has to be in the format" +
                    " deadline <task> /by <date and time>");
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!! Format of the date is wrong");
        }
    }

    /**
     * Creates and event object and stores it
     * @param item the user's input
     */
    public void createEvent(String item) {
        try {
            int slash = 0;
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) == '/') {
                    slash = i;
                    break;
                }
            }
            list.add(new Event(item.substring(6, slash - 1), item.substring(slash + 4)));
            System.out.println("Got it. I've added this task:");
            System.out.println(list.get(list.size() - 1).getTask());
            storage.writeToFile();
            count = count + 1;
            System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a event has to be in the format event" +
                    " <task> /at <date and time>");
        }
    }

    /**
     * Creates a todo object and stores it
     * @param item The user's input
     */
    public void createTask(String item) {
        try {
            list.add(new Todo(item.substring(5)));
            System.out.println("Got it. I've added this task:");
            System.out.println(list.get(list.size() - 1).getTask());
            storage.writeToFile();
            count = count + 1;
            System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Deletes a task from the list
     * @param index Index of the task
     */
    public void deleteTask(int index) {
        try {
            String text = list.get(index).getTask();
            list.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(text);
            storage.writeToFile();
            count = count - 1;
            System.out.println("Now you have " + Integer.toString(count) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Looks like the task number is incorrect :(");
        }
    }

}

