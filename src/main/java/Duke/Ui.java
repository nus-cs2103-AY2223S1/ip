package Duke;

import Tasks.*;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you? ^_^");
    }

    public void printExit() {
        this.scanner.close();
        System.out.println("\tBye. Hope to see you again soon :D");
    }

    public void printAddTask(Task addedTask, int numOfTasks) {
        String header = "\tGot it! I have added this task:\n\t\t" + addedTask;
        String tasks = String.format("\n\tNow you have %d %s in the list!", numOfTasks,
                numOfTasks < 2 ? "task" : "tasks");
        System.out.println(header + tasks);
    }

    public void printDeleteTask(Task deletedTask, int numOfTasks) {
        String msg = String.format("\tNoted, I have removed this task:\n\t\t%s", deletedTask);
        System.out.println(msg);
    }

    public void printMarkTask(Task task) {
        String msg = String.format("\tNice! I have marked this task as done:\n\t\t%s",
                task);
        System.out.println(msg);
    }

    public void printUnmarkTask(Task task) {
        String msg = String.format("\tOkay! I have marked this task as not done:\n\t\t%s",
                task);
        System.out.println(msg);
    }

    public void printException(Exception exception) {
        System.out.println("\t" + exception.getMessage());
    }

    public String input() {
        return this.scanner.nextLine();
    }

    public void printInvalid() throws DukeException {
        throw new DukeException("I don't know what this means :(");
    }

    public void printList(String list) {
        System.out.println(list);
    }
}
