import java.util.Scanner;

public class Ui {
    public Ui() {}

    public void showWelcome() {
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
    }

    public void printDivider() {
        System.out.println("-----------------------------------");
    }

    public void printError(Exception e) {
        System.out.println(e.toString());
    }

    public void printGetUserCommand() {
        System.out.println("Please enter a command: ");
    }

    public String readUserCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskList(TaskList taskList) {
       for (int i = 1; i < taskList.size(); i++) {
           System.out.format("%s. %s\n", i, taskList.get(i - 1));
       }
    }

    public static void printMarkTask(Task task) {
        System.out.format("Nice! I've marked this task as done: %s\n", task);
    }

    public static void printUnmarkTask(Task task) {
        System.out.format("OK, I've marked this task as not done yet: %s\n", task);
    }

    public static void printAddTask(Task task, TaskList taskList) {
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    public static void printDeleteTask(Task task, TaskList taskList) {
        System.out.format("Noted. I've removed this task:\n %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    public static void printCreateNewDirectory(String dirName) {
        System.out.printf("Creating new directory '%s' to store data...\n", dirName);
    }

    public static void printCreateNewStorage(String storageName) {
        System.out.printf("Creating new file '%s' to store data...\n", storageName);
    }

    public static void printSaving() {
        System.out.println("Saving...");
    }
}
