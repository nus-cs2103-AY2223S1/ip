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
     * Add a new task.
     * @param str the description of task.
     * @return a newly created Task class for storage.
     */
    public static Task add(String str) {
        printLine();
        Task task = Task.addTask(str);
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

    public static void delete(Task task) {
        System.out.println("     Noted. I've removed this task:");
    }

    /**
     * Helper func to: print a horizontal line.
     */
    private static void printLine() {
        System.out.println("    --------------------------------------------------------------------------------------");
    }
}
