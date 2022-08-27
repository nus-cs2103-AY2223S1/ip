package duke.ui;

public class Ui {

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

    public static void goodbye() {
        Ui.showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        Ui.showLine();
    }

    public static void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void addTaskMessage(String taskDescription, int size) {
        Ui.showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", taskDescription));
        System.out.println(String.format("\tNow you have %d tasks in the list.", size));
        Ui.showLine();
    }

    public static void removeTaskMessage(String taskDescription, int size) {
        Ui.showLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println(String.format("\t  %s", taskDescription));
        System.out.println(String.format("\tNow you have %d tasks in the list.", size));
        Ui.showLine();
    }

    public static void markTaskMessage(String taskDescription) {
        Ui.showLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + taskDescription);
        Ui.showLine();
    }

    public static void unmarkTaskMessage(String taskDescription) {
        Ui.showLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + taskDescription);
        Ui.showLine();
    }

    public static void showExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
