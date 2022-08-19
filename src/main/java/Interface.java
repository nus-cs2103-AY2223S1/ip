import java.util.ArrayList;

public class Interface {
    /**
     *  Greet
     */
    public static void greet() {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }
    /**
     *  Goodbye
     */
    public static void bye() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

/*    public static void echo(String str) {
        printLine();
        System.out.println("     " + str);
        printLine();
    }*/

    /**
     * Add a new Deadline.
     * @param str the description of ddl + by when
     * @return a newly created Deadline class for storage.
     */
    public static Deadline addDeadline(String str) {
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
     * Add a new Event.
     * @param str the description of event + at what time
     * @return a newly created Event class for storage.
     */
    public static Event addEvent(String str) {
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
     * Add a new ToDo task.
     * @param str the description of to do task.
     * @return a newly created ToDo class for storage.
     */
    public static ToDo addToDo(String str) {
        printLine();
        System.out.println("     Got it. I've added this task:");
        ToDo task = ToDo.addTask(str);
        ToDo.countTaskLeft();
        printLine();
        return task;
    }

    /**
     * Print current task list.
     * @param taskList task list retrieved directly from the storage.
     */
    public static void list(ArrayList<Task> taskList) {
        printLine();
        for (Task task : taskList) {
            System.out.println(task);
        }
        printLine();
    }

    /**
     * Mark task with index specified as done.
     * @param task Mark task with index specified fetched from the task list.
     */
    public static void mark(Task task) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        task.changeStatus(); // flip status
        System.out.println("       " + task.printSelf());
        printLine();
    }

    /**
     * Mark task with index specified as not done.
     * @param task Mark task with index specified fetched from the task list.
     */
    public static void unmark(Task task) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        task.changeStatus(); // flip status
        System.out.println("       " + task.printSelf());
        printLine();
    }

    /**
     *      Noted. I've removed this task:
     *        [E][ ] project meeting (at: Aug 6th 2-4pm)
     *      Now you have 4 tasks in the list.
     * @param task
     */
    public static void delete(Task task) {
        printLine();
        System.out.println("     Noted. I've removed this task:");
        Task.removeTask(task); // change total number of task.
        ToDo.countTaskLeft();
        printLine();
    }

    /**
     * Helper func to: print a horizontal line.
     */
    private static void printLine() {
        System.out.println("    --------------------------------------------------------------------------------------");
    }
}
