package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {

    private boolean isExit = false;
    private static Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public boolean isContinue() {
        return !this.isExit;
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon");
    }

    public void listTasks(TaskList taskList) throws DukeException {
        try {
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.getTask(i));
            }
            System.out.println("That's all!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }

    public void markTask(int index, Task task) {
        System.out.println("Marked following task as done:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void unmarkTask(int index, Task task) {
        System.out.println("Marked following task as not done:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void deleteTask(int index, Task task) {
        System.out.println("The following task is deleted:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    public void addTask(Task task) {
        System.out.println("Got it! I stored this task:\n" + task);
    }

    public void showTaskListCapacity(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
    }

    public void handleException(Exception e) {
        System.out.println(e);
    }

    public void exit() {
        this.isExit = true;
    }
}
