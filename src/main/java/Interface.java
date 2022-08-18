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
     * Helper func to: print a horizontal line.
     */
    private static void printLine() {
        System.out.println("    --------------------------------------------------------------------------------------");
    }
}
