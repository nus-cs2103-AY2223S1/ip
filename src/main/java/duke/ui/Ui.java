package duke.ui;
import duke.tasklist.TaskList;

import java.io.PrintStream;
import java.util.Scanner;
public class Ui {

    private Scanner sc;
    private PrintStream out;

    public Ui() {
        sc = new Scanner(System.in);
        out = System.out;
    }

    public void showGreetings() {
        String greetings = "_________________________________________________\nHello! I'm Duke"
                + "\nWhat can I do for you?\n_________________________________________________";
        this.out.println(greetings);
    }

    public static void showBye() {
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n"
                + "_________________________________________________\n");
    }

    public static void showDirectoryCreation() {
        System.out.println("Creating new /data/ directory folder.");
    }

    public static void showFileCreation() {
        System.out.println("Creating new duke.txt file under /data/ directory folder.");
    }

    public static void showLoadSuccessful() {
        System.out.println("Loaded successfully!");
    }

    public static void showSaveSuccessful() {
        System.out.println("Saved successfully!");
    }

    public static void showTasksInList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.taskListSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }

    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.latestTask() + "\nNow you have " + taskList.taskListSize() +
                " tasks in the list.");
    }

    public void showTaskDeleted(TaskList taskList, int integer) {
        System.out.println("Noted. I've removed this task:\n"
                + taskList.getTask(integer));
        System.out.println("Now you have " + (taskList.taskListSize() - 1) + " tasks in the list.");
    }

    public void showMarkAsDone(TaskList taskList, int integer) {
        System.out.println("Nice! I've marked this task as done:\n"
                + taskList.getTask(integer - 1).toString());
    }

    public void showMarkAsNotDone(TaskList taskList, int integer) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + taskList.getTask(integer - 1).toString());
    }

}
