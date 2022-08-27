package skyler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Contains the task list
 */
public class TaskList {

    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object
     *
     * @param storage Storage object from which to retrieve previous tasks and save new tasks to.
     * @param tasks List of tasks.
     */
    public TaskList(Storage storage, ArrayList<Task> tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Creates a TaskList object
     *
     * @param storage Storage object from which to retrieve previous tasks and save new tasks to.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints the current list of tasks
     */
    public void list() {
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String str = String.format("%d.%s", i + 1, tasks.get(i));
            System.out.println(str);
        }
    }

    /**
     * Marks a task as done
     *
     * @param item Index of task.
     */
    public void mark(int item) {
        Task currTask = tasks.get(item - 1);
        currTask.markAsDone();
        System.out.println("You have completed this task:");
        String show = String.format("  %s", currTask);
        System.out.println(show);

        try {
            // task list changes
            storage.saveTask(tasks);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Marks a task as not done
     *
     * @param item Index of task.
     */
    public void unmark(int item) {
        Task currTask = tasks.get(item - 1);
        currTask.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        String show = String.format("  %s", currTask);
        System.out.println(show);

        try {
            // task list changes
            storage.saveTask(tasks);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Adds a new todo task to the list
     *
     * @param desc Task description.
     */
    public void todo(String desc) {
        Todo newTodo = new Todo(desc);
        tasks.add(newTodo);

        printTask(newTodo, tasks.size());

        try {
            // task list changes
            storage.saveTask(tasks);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Adds a new deadline to the list
     *
     * @param descWithDate Task description with date.
     */
    public void deadline(String descWithDate) {
        String[] arr1 = descWithDate.split(" /by ", 2);

        // process date and time
        LocalDateTime dt = processDateTime(arr1[1]);

        Deadline newDeadline = new Deadline(arr1[0], dt);
        tasks.add(newDeadline);

        printTask(newDeadline, tasks.size());

        try {
            // task list changes
            storage.saveTask(tasks);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Adds a new event to the list
     *
     * @param descWithDate Task description with date.
     */
    public void event(String descWithDate) {
        String[] arr1 = descWithDate.split(" /at ", 2);

        // process date and time
        LocalDateTime dt = processDateTime(arr1[1]);

        Event newEvent = new Event(arr1[0], dt);
        tasks.add(newEvent);

        printTask(newEvent, tasks.size());

        try {
            // task list changes
            storage.saveTask(tasks);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Deletes task from the list
     *
     * @param item Index of task.
     */
    public void delete(int item) {
        Task currTask = tasks.get(item - 1);

        System.out.println("The following task will be removed:");
        String show = String.format("  %s", currTask);
        System.out.println(show);

        tasks.remove(item - 1);

        String str = String.format("Total number of tasks: %d", tasks.size());
        System.out.println(str);

        try {
            // task list changes
            storage.saveTask(tasks);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Prints action taken and task summary
     *
     * @param task The task added.
     * @param num Current total number of tasks.
     */
    public static void printTask(Task task, int num) {
        System.out.println("I've added the following task:");
        String str = String.format("  %s", task);
        System.out.println(str);
        String summary = String.format("Total number of tasks: %d", num);
        System.out.println(summary);
    }

    /**
     * Formats date and time information and returns a LocalDateTime object
     *
     * @param strDateTime String representation of date and time.
     * @return Corresponding LocalDateTime object.
     */
    public static LocalDateTime processDateTime(String strDateTime) {
        String[] timeInfo = strDateTime.split(" ", 2);

        String unformattedTime = timeInfo[1];
        String hour = unformattedTime.substring(0, 2);
        String minute = unformattedTime.substring(2, 4);
        String formattedTime = String.format("%s:%s", hour, minute);

        LocalDate date = LocalDate.parse(timeInfo[0]);
        LocalTime time = LocalTime.parse(formattedTime);
        return LocalDateTime.of(date, time);
    }
}
