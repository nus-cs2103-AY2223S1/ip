package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    protected Scanner scanner;

    /**
     * Handles all system output messages.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
    }

    public void goodBye() {
        System.out.println("Bye! I have saved your tasks");
        System.out.println("Hope to see you again soon! :)");
    }

    public void showLoadingError(String errorMessage) {
        System.out.printf("UI Error! %s\n", errorMessage);
    }

    public void showError(String errorMessage) {
        System.out.printf("Error! %s\n", errorMessage);
    }

    public void showNothingToDoMessage() {
        System.out.println("You have nothing to do!");
    }

    public void listAllTasks(TaskList tasks) {
        System.out.println("Here are the tasks I found:");
        tasks.forEach((task) -> {
            int itemNumber = tasks.indexOf(task) + 1;
            System.out.printf("%d: %s\n", itemNumber, task);
        });
    }

    public void listFilteredTasks(TaskList originalTasks, TaskList filteredTasks) {
        System.out.println("Here are the tasks I found:");
        filteredTasks.forEach((task) -> {
            int itemNumber = originalTasks.indexOf(task) + 1;
            System.out.printf("%d: %s\n", itemNumber, task);
        });
    }

    public void showMarkedAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showMarkedAsNotDoneMessage(Task task) {
        System.out.println("Alright! I've unmarked this task:");
        System.out.println(task);
    }

    public void showAddedTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    public void showRemovedTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void showAddedTagMessage(Task task) {
        System.out.println("Noted. I've tagged the tag to this task:");
        System.out.println(task);
    }

    public void showRemovedTagMessage(Task task) {
        System.out.println("Noted. I've removed the tag to this task:");
        System.out.println(task);
    }

    public void countTasks(TaskList tasks) {
        System.out.printf("Now you have %d tasks in the list.", tasks.size());
    }

}
