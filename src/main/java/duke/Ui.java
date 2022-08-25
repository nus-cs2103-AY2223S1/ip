package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;


public class Ui {

    private static Scanner sc = new Scanner(System.in);

    public Command run(String input) throws DukeException {
        Parser parser = new Parser();
        return parser.parse(input);
    }

    public String readCommand() {
        return sc.nextLine();
    }
    public void showLine() {
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void showGreetMsg() {
        String out = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(out);
    }

    public void showUnknownMsg() {
        System.out.println("I'm sorry, but I don't know what that means :-(");
    }

    public void showAddMsg(Task task, int cnt) {
        String plural = cnt == 1
                ? "task"
                : "tasks";

        String out = "Got it. I've added this task:\n "
                + task + "\nNow you have "
                + cnt + " " + plural + " in the list.";
        System.out.println(out);
    }

    public void showDeleteMsg(Task task, int size) {
        String info = task.toString();
        String plural = size - 1 <= 1
                ? "task"
                : "tasks";
        String out = "Noted. I've removed this task:\n  "
                 + info + "\n Now you have " + size
                + " " + plural + " in the list";
        System.out.println(out);
    }

    public void showExitMsg() {
        String out = "Bye. Hope to see you again soon!";
        System.out.println(out);
    }

    public void showList() {
        System.out.println("Your List :");
    }

    public void showTask(int index, Task task) {
        System.out.println(index + "." + task.toString());
    }

    public void showMarkMsg(Task task) {
        String out = "Nice! I've marked this task as done:\n  " + task.toString();
        System.out.println(out);
    }

    public void showUnmarkMsg(Task task) {
        String out = "OK, I've marked this task as not done yet:\n  " + task.toString();
        System.out.println(out);
    }

    public void showFindMsg() {
        System.out.println("Here are the matching tasks in your list:\n");
    }

    public void showFindEmptyMsg() {
        System.out.println("There are no matching task in your list\n");
    }

    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public void showFileNotFound(String msg) {
        System.out.println(msg);
    }


}
