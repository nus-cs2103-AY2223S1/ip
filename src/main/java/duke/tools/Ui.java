package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

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

    public void sayTasks(TaskList taskList) throws DukeException {
        try {
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.getTask(i));
            }
            System.out.println("That's all!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
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

    public void sayExceptionMessage(Exception e) {
        System.out.println(e);
    }

    public void exit() {
        this.isContinue = false;
    }
}
