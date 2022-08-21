package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printDeadLineErrorMessage() throws DukeException {
        throw new DukeException("Oops! Remember to include /by and the deadline after your task description");
    }

    public void printEventErrorMessage() throws DukeException{
        throw new DukeException("Oops! Remember to include /at and the event time after your task description");
    }

    public void printListTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void printListTasksDueOnDateMessage(LocalDate date) {
        System.out.printf("Here are the tasks due on %s\n", date);
    }

    public void printNoTasksMessage() {
        System.out.println("Looks like you have no tasks occurring on that day");
    }

    public void printMarkTaskMessage(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
    }

    public void printUnMarkTaskMessage(Task task) {
        System.out.printf("OK, I've marked this task as not done yet:\n%s\n", task);
    }

    public void printDeleteTaskMessage(Task deletedTask, int totalTasks) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n",
                deletedTask, totalTasks);
    }

    public void printTaskAddedMessage(Task task, int totalTasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                task, totalTasks);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public boolean hasNext() {
        return sc.hasNext();
    }

    public void showError(String message) {
        System.out.printf("Seems like something went wrong\n%s", message);
    }
}
