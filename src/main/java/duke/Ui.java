package duke;

import duke.DukeException.DukeException;
import java.io.FileNotFoundException;
import duke.DukeException.DateTimeFormatException;
import duke.Storage.Cache;
import duke.Storage.TaskList;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.ToDo;

import java.io.File;

public class Ui {
    /**
     *  Greets.
     */
    public static void greet() {
        printLine();
        System.out.println("     Hello! I'm Duke.");
        System.out.println("     What can I do for you?");
        printLine();
    }
    /**
     *  Says goodbye.
     */
    public static void bye() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Add a new duke.Tasks.Deadline.
     * @param str the description of ddl + by when
     * @return a newly created duke.Tasks.Deadline class for storage.
     */
    public static Deadline addDeadline(String str) throws DateTimeFormatException {
        String name = str.split(" /", 2)[0];
        String by = str.split(" /", 2)[1].split("by ", 2)[1];

        printLine();
        System.out.println("     Got it. I've added this task:");
        Deadline task = Deadline.addTask(name, by);
        Deadline.countTaskLeft();
        printLine();
        return task;
    }

    /**
     * Adds a new duke.Tasks.Event.
     * @param str The description of event + at what time
     * @return A newly created duke.Tasks.Event class for storage.
     */
    public static Event addEvent(String str) throws DateTimeFormatException {
        String name = str.split(" /", 2)[0];
        String at = str.split(" /", 2)[1].split("at ", 2)[1];

        printLine();
        System.out.println("     Got it. I've added this task:");
        Event task = Event.addTask(name, at);
        Event.countTaskLeft();
        printLine();
        return task;
    }

    /**
     * Adds a new duke.Tasks.ToDo task.
     * @param str The description of to do task.
     * @return A newly created duke.Tasks.ToDo class for storage.
     */
    public static ToDo addToDo(String str) throws DateTimeFormatException {
        printLine();
        System.out.println("     Got it. I've added this task:");
        ToDo task = ToDo.addTask(str);
        ToDo.countTaskLeft();
        printLine();
        return task;
    }

    /**
     * Prints current task list.
     * @param taskList The task list retrieved directly from the storage.
     */
    public static void list(TaskList taskList) {
        printLine();
        for (Task task : taskList.getList()) {
            System.out.println(task);
        }
        printLine();
    }

    /**
     * Marks task with index specified as done.
     * @param task Mark task with index specified fetched from the task list.
     */
    public static void mark(Task task) throws DateTimeFormatException {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        task.changeStatus(); // flip status
        System.out.println("       " + task.printSelf());
        printLine();
    }

    /**
     * Marks task with index specified as not done.
     * @param task Mark task with index specified fetched from the task list.
     */
    public static void unmark(Task task) throws DateTimeFormatException {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        task.changeStatus(); // flip status
        System.out.println("       " + task.printSelf());
        printLine();
    }

    /**
     * Deletes a task from the list and prints out the task.
     * @param task The duke.Tasks.Task object which the user wants to delete
     */
    public static void delete(Task task) throws DateTimeFormatException {
        printLine();
        System.out.println("     Noted. I've removed this task:");
        Task.removeTask(task); // change total number of task.
        ToDo.countTaskLeft();
        printLine();
    }

    /**
     * Syncs a duke.Tasks.Deadline from cache file.
     * @param str The description of ddl + by when.
     * @param isDone A boolean indicator to show if the task is marked done.
     * @return A newly created duke.Tasks.Deadline class for storage.
     */
    public static Deadline syncDeadline(String str, boolean isDone) throws DateTimeFormatException {
        String name = str.split(" \\| ",2)[0];
        String by = str.split(" \\| ",2)[1];

        Deadline task = Deadline.addTask(name, by);
        if (isDone) {
            task.changeStatus();
        }
        return task;
    }

    /**
     * Syncs a duke.Tasks.Event from cache file.
     * @param str The description of duke.Tasks.Event + at what time.
     * @param isDone A boolean indicator to show if the task is marked done.
     * @return A newly created duke.Tasks.Event class for storage.
     */
    public static Event syncEvent(String str, boolean isDone) throws DateTimeFormatException {
        String name = str.split(" \\| ",2)[0];
        String at = str.split(" \\| ",2)[1];

        Event task = Event.addTask(name, at);
        if (isDone) {
            task.changeStatus();
        }
        return task;
    }

    /**
     * Syncs a Todo from cache file.
     * @param name The description of Todo
     * @param isDone A boolean indicator to show if the task is marked done.
     * @return A newly created Todo class for storage.
     */
    public static ToDo syncToDo(String name, boolean isDone) throws DateTimeFormatException {
        ToDo task = ToDo.addTask(name);
        if (isDone) {
            task.changeStatus();
        }
        return task;
    }

    public static TaskList loading(File file) throws DukeException, FileNotFoundException {
        printLine();
        System.out.println("     \uD83D\uDE0A Heyyo!!! Some saved work found in: " + file.getAbsolutePath() + "\n     Loading...");
        TaskList taskList = Cache.recovery(file);
        System.out.println("     \uD83D\uDE0A Done loading~ Thanks for waiting!!!");
        printLine();
        return taskList;
    }

    /**
     * Helper func to print a horizontal line.
     */
    private static void printLine() {
        System.out.println("    --------------------------------------------------------------------------------------");
    }
}
