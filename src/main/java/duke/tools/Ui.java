package duke.tools;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * This class takes care of the interaction with the user.
 * This class manages the input and output from the Duke system.
 */
public class Ui {

    private boolean isContinue = true;
    private static Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public boolean canContinue() {
        return this.isContinue;
    }

    public void sayGreet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void sayBye() {
        System.out.println("Bye! Hope to see you again soon");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showTaskWithIndex(Task task, int index) {
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void finishListing() {
        System.out.println("That's all!");
    }

    public void sayMarkTask(int index, Task task) {
        System.out.println("Marked following task as done:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void sayUnmarkTask(int index, Task task) {
        System.out.println("Marked following task as not done:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void sayDeleteTask(int index, Task task) {
        System.out.println("The following task is deleted:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void sayAddTask(Task task) {
        System.out.println("Got it! I stored this task:\n" + task);
    }

    public void sayTaskListSize(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
    }

    public void find() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void sayExceptionMessage(Exception e) {
        System.out.println(e);
    }

    public void exit() {
        this.isContinue = false;
    }
}
