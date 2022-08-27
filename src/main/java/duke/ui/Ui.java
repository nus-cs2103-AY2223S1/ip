package duke.ui;

/**
 * Text UI of Duke.
 */
public class Ui {

    /**
     * A logo and welcome text to be shown when Duke is first initialised.
     */
    public static void greeting() {
        String logo = "\t\t\t  ____        _        \n"
        + "\t\t\t |  _ \\ _   _| | _____ \n"
        + "\t\t\t | | | | | | | |/ / _ \\\n"
        + "\t\t\t | |_| | |_| |   <  __/\n"
        + "\t\t\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Ui.showLine();
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        Ui.showLine();
    }

    /**
     * A goodbye text when Duke is closed.
     */
    public static void goodbye() {
        Ui.showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        Ui.showLine();
    }

    /**
     * A decorative divider line.
     */
    public static void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * A message to acknowledge that the task has been added to the task list.
     *
     * @param taskDescription Description of the task that has been added to the task list.
     * @param size Size of the task list after the task has been added.
     */
    public static void addTaskMessage(String taskDescription, int size) {
        Ui.showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", taskDescription));
        System.out.println(String.format("\tNow you have %d tasks in the list.", size));
        Ui.showLine();
    }

    /**
     * A message to acknowledge that the task has been removed to the task list.
     *
     * @param taskDescription Description of the task that has been removed to the task list.
     * @param size Size of the task list after the task has been removed.
     */
    public static void removeTaskMessage(String taskDescription, int size) {
        Ui.showLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println(String.format("\t  %s", taskDescription));
        System.out.println(String.format("\tNow you have %d tasks in the list.", size));
        Ui.showLine();
    }

    /**
     * A message to acknowledge that the task has been marked as complete.
     *
     * @param taskDescription Description of the task that has been marked as complete.
     */
    public static void markTaskMessage(String taskDescription) {
        Ui.showLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + taskDescription);
        Ui.showLine();
    }

    /**
     * A message to acknowledge that the task has been marked as incomplete.
     *
     * @param taskDescription Description of the task that has been marked as incomplete.
     */
    public static void unmarkTaskMessage(String taskDescription) {
        Ui.showLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + taskDescription);
        Ui.showLine();
    }

    /**
     * Method to print the exception message.
     *
     * @param e Exception that will have its message printed.
     */
    public static void showExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
