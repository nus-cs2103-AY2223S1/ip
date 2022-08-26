package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    public static String DIVIDER = "____________________________________________________________\n";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    private void generateReply(String reply) {
        System.out.print(DIVIDER + reply + "\n" + DIVIDER + "\n");
    }

    public void showError(String errorMessage) {
        System.err.println(DIVIDER + errorMessage + "\n" + DIVIDER);
    }

    public void greet() {
        generateReply("Hello! I'm Sheep\n" +
                "What can I do for you?");
    }

    public void showLoadingError() {
        showError("Cannot find the file from given file path.\n" +
                "Create a new to-do list.");
    }

    public void showAddTask(Task task, int size) {
        generateReply("Added " + task.toString() + " to the list.\n" +
                "There are " + size + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        generateReply(tasks.toString());
    }

    public void showMarkDone(Task task) {
        generateReply("Nice! I have marked this task as done\n" +
                task.toString());
    }

    public void showUnmarkDone(Task task) {
        generateReply("Oh no! I have marked this task as not done\n" +
                task.toString());
    }

    public void showRemoveTask(Task task) {
        generateReply("Done! I have removed this task from your todo list\n" +
                task.toString());
    }

    public void showBye() {
        generateReply("Ok bye, see you later.");
        sc.close();
    }

    public String readInput() {
        return sc.nextLine();
    }
}
