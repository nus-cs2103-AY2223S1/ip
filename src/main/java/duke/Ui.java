package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;

public class Ui {
    private static Scanner sc = new Scanner(System.in);
    private static final String LINE = "-----------------------------------------"
            + "---------------------------------------------";

    public Command run(String input) throws DukeException {
        return Parser.parse(input);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showGreetMessage() {
        String out = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(out);
    }

    public void showUnknownMessage() {
        System.out.println("I'm sorry, "
                + "but I don't know what that means :-(");
    }

    public void showAddMessage(Task task, int size) {
        String plural = size == 1
                ? "task"
                : "tasks";
        String out = "Got it. I've added this task:\n "
                + task + "\nNow you have "
                + size + " " + plural + " in the list.";
        System.out.println(out);
    }

    public void showDeleteMessage(Task task, int size) {
        String info = task.toString();
        String plural = size - 1 <= 1
                ? "task"
                : "tasks";
        String out = "Noted. I've removed this task:\n  "
                 + info + "\n Now you have " + size
                + " " + plural + " in the list";
        System.out.println(out);
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList() {
        System.out.println("Your List :");
    }

    public void showTask(int index, Task task) {
        System.out.println(index + "." + task.toString());
    }

    public void showMarkMessage(Task task) {
        String out = "Nice! I've marked this task as done:\n  "
                + task.toString();
        System.out.println(out);
    }

    public void showUnmarkMessage(Task task) {
        String out = "OK, I've marked this task as not done yet:\n  "
                + task.toString();
        System.out.println(out);
    }

    public void showLoadingError(String message) {
        System.out.println(message);
    }

    public void showFileNotFound(String message) {
        System.out.println(message);
    }

    public void showReadMessage() {
        System.out.println("Reading files and loading tasks");
    }
}
