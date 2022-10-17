package duke.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import duke.dukeexception.DateTimeFormatException;
import duke.dukeexception.DukeException;
import duke.storage.Cache;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


/**
 * The user interface.
 */
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
     * Add a new Deadline.
     * @param str the description of ddl + by when
     * @return a newly created Deadline class for storage.
     */
    public static Deadline addDeadline(String str) throws DateTimeFormatException {
        String name = str.split(" /", 2)[0];
        String by = str.split(" /", 2)[1].split("by ", 2)[1];

        printLine();
        Deadline task = Deadline.addTask(name, by);
        Deadline.countTaskLeft();
        printLine();
        return task;
    }

    /**
     * Adds a new Event.
     * @param str The description of event + at what time
     * @return A newly created Event class for storage.
     */
    public static Event addEvent(String str) throws DateTimeFormatException {
        String name = str.split(" /", 2)[0];
        String at = str.split(" /", 2)[1].split("at ", 2)[1];

        printLine();
        Event task = Event.addTask(name, at);
        Event.countTaskLeft();
        printLine();
        return task;
    }

    /**
     * Adds a new ToDo task.
     * @param str The description of to do task.
     * @return A newly created ToDo class for storage.
     */
    public static ToDo addToDo(String str) throws DateTimeFormatException {
        printLine();
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
        String name = str.split(" \\| ", 2)[0];
        String by = str.split(" \\| ", 2)[1];

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
        String name = str.split(" \\| ", 2)[0];
        String at = str.split(" \\| ", 2)[1];

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

    /**
     * Loads previous cache file contents and adds to current task list.
     * @param file The duke.txt in .\data\ folder
     * @return The synced task list.
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public static TaskList loading(File file) throws DukeException, FileNotFoundException {
        printLine();
        System.out.println("     :) Heyyo!!! Some saved work found in: "
                + file.getAbsolutePath() + "\n     Loading...");
        TaskList taskList = Cache.recover(file);
        System.out.println("     :) Done loading~ Thanks for waiting!!!");
        printLine();
        return taskList;
    }

    /**
     * Prints list of tasks that satisfies the requirements.
     * @param str The substring.
     * @param taskList The TaskList storage.
     */
    public static void find(String str, TaskList taskList) throws DateTimeFormatException {
        ArrayList<Task> findList = new ArrayList<Task>();
        String[] strList = str.split(" ");
        Integer index = 0;
        printLine();
        for (Task task : taskList.getList()) {
            if (task.hasName(strList)) {
                findList.add(task);
            }
        }
        System.out.println("     Here are the matching tasks in your list:");
        for (Task task : findList) {
            index = index + 1;
            System.out.println("     " + index + "." + task.printSelf());
        }
        printLine();
    }

    /**
     * Prints msg to show the user last command has been undone.
     * @param stack The taskList history of Duke for this run only.
     */
    public static void undo(Stack<String> stack) {
        printLine();
        if (stack.isEmpty()) {
            System.out.println("     Opps:( Nothing has been done, cos there is no history.");
        } else {
            System.out.println("     Last command has been undone. Yay!");
        }
        printLine();
    }

    /**
     * Helper func to print a horizontal line.
     */
    private static void printLine() {
        System.out.println("    ---------------------------------------------------------");
    }
}
