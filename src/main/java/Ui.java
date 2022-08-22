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

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskList(TaskList taskList) {
       for (int i = 1; i < taskList.size(i); i++) {
           System.out.format("%s. %s\n", i, taskList.get(i - 1));
       }
    }

    public void printMarkTask(Task task) {
        System.out.format("Nice! I've marked this task as done: %s\n", task);
    }

    public void printUnmarkTask(Task task) {
        System.out.format("OK, I've marked this task as not done yet: %s\n", task);
    }

    public void printAddTask(Task task, TaskList taskList) {
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    public void printDeleteTask(Task task, TaskList taskList) {
        System.out.format("Noted. I've removed this task:\n %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    public void printCreateNewDirectory(String dirName) {
        System.out.printf("Creating new directory '%s' to store data...\n", dirName);
    }

    public void printCreateNewStorage(String storageName) {
        System.out.printf("Creating new file '%s' to store data...\n", storageName);
    }

    public void printSaving() {
        System.out.println("Saving...");
    }
}
