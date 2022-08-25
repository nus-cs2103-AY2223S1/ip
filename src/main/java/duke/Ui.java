package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public Ui() {};

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTasks(TaskList tasks) {
        System.out.println(tasks);
    }

    public void showTaskMarked(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task);
    }

    public void showTaskAdded(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
    }

    public void showTaskDeleted(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
    }

    public void showTaskCount(int count) {
        if (count == 1) {
            System.out.println("\tNow you have 1 task in the list.");
        } else {
            System.out.println("\tNow you have " + (count) + " tasks in the list.");
        }
    }
}
