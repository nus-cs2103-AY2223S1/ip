package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private Boolean isExit = false;
    private static Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public Boolean canContinue() {
        return !isExit;
    }

    public void printGreeting() {
        System.out.println("Hello!\nHow may i help you today?");
    }

    public void printFarewell() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void printListStatus(TaskList taskList) throws DukeException {
        try {
            if (taskList.getSize() == 0) {
                System.out.println("There are currently no tasks in your list");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.getSize(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, taskList.getTask(i)));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    public void markAsDone(int index, Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + String.format("%d. %s", index - 1, task));
    }

    public void markAsUndone(int index, Task task) {
        System.out.println("Ok! I've marked this task as not done yet:\n"
                + String.format("%d. %s", index - 1, task));
    }

    public void deleteTask(int index, Task task) {
        System.out.println("Noted. I've removed this task:\n"
                + String.format("%d. %s", index + 1, task));
    }

    public void addTask(Task task) {
        System.out.println("Got it! I've added this task:\n"
                + "> " + task);
    }

    public void printListSize(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void exit() {
        isExit = true;
    }
}
