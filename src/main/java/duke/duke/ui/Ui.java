package duke.duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    private void print(String s) {
        System.out.println("\t " + s);
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        print("Hello! I'm duke.Duke");
        print("What can I do for you?");
        printLine();
    }

    public void printExit() {
        print("Bye. Hope to see you again soon!");
    }

    public void printTasks(TaskList tasks) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            print(String.format("%d. %s", i + 1, tasks.getTask(i)));
        }
    }

    public void printAddTasks(Task task, TaskList tasks) {
        print("Got it. I've added this duke.task:");
        print("  " + task.toString());
        print("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void printMark(Task task) {
        print("Nice! I've marked this duke.task as done:");
        print("  " + task.toString());
    }

    public void printUnMark(Task task) {
        print("OK, I've marked this duke.task as not done yet:");
        print("  " + task.toString());
    }

    public void printDeleteTask(Task task, TaskList tasks) {
        print("Got it. I've added this duke.task:");
        print("  " + task.toString());
        print("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void printException(Exception e) {
        printLine();
        print(e.getMessage());
    }

    public String nextLine() {
        return this.sc.nextLine();
    }

    public void close() {
        this.sc.close();
    }
}
