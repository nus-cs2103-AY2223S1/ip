package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Predicate;

public class Ui {

    protected Scanner scanner;

    /**
     * Handles all system output messages.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_".repeat(50));
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
        System.out.println("Here are the tasks in your list:");
        tasks.forEach((task) -> {
            int itemNumber = tasks.indexOf(task) + 1;
            System.out.printf("%d: %s\n", itemNumber, task);
        });
    }

    public void showMarkedAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showMarkedAsNotDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
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
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }

    public void showTasksWithThisPropertyAndMessage(
            TaskList tasks,
            Predicate<? super Task> pred,
            String messageIfPresent,
            String messageIfAbsent) {

        boolean[] hasElements = {false};
        tasks
                .stream()
                .filter(pred)
                .forEach((t) -> {
                    int itemNumber = tasks.indexOf(t) + 1;
                    if (!hasElements[0]) {
                        System.out.println(messageIfPresent);
                    }
                    hasElements[0] = true;
                    System.out.printf("%d: %s\n", itemNumber, t);
                });
        if (!hasElements[0]) {
            System.out.println(messageIfAbsent);
        }
    }

    public void showTasksWithThisProperty(TaskList tasks, Predicate<? super Task> pred) {
        this.showTasksWithThisPropertyAndMessage(tasks, pred, "Here are the tasks", "No tasks were found");
    }

}
