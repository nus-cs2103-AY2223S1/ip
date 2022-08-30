package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String FORMAT_TAB = "    ";
    private static final String FORMAT_LINEBREAK = "___________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void printTab(String content) {
        System.out.println(FORMAT_TAB + content);
    }

    public void showLine() {
        printTab(FORMAT_LINEBREAK);
    }

    public void printGreetings() {
        showLine();
        printTab("Hello! I'm Duke");
        printTab("What can I do for you?");
        showLine();
        System.out.println();
    }

    public void printAddingTask(Task t, TaskList tasks) {
        showLine();
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp = String.format("Now you have %d tasks in the list.", tasks.size());
        printTab(temp);
        showLine();
    }

    public void printGoodbye() {
        showLine();
        printTab("Bye. Hope to see you again soon!");
        showLine();
        System.out.println();
    }

    public void printError(Exception e) {
        showLine();
        printTab(e.getMessage());
        showLine();
    }

    public void printMarked(Task t) {
        showLine();
        printTab("Nice! I've marked this task as done:");
        printTab("  " + t);
        showLine();
    }

    public void printUnMarked(Task t) {
        showLine();
        printTab("OK, I've marked this task as not done yet:");
        printTab("  " + t);
        showLine();
    }

    public void printCurrentList(TaskList tasks) {
        showLine();
        printTab("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String temp = String.format("%d. %s", i + 1, t);
            printTab(temp);
        }
        showLine();
    }

    public void echo(String s) {
        showLine();
        printTab(s);
        showLine();
        System.out.println();
    }

    public void printDelete(Task t, TaskList tasks) {
        showLine();
        printTab("Noted. I've removed this task:");
        printTab("  " + t);
        String temp = String.format("Now you have %d tasks in the list.", tasks.size());
        printTab(temp);
        showLine();
    }
}
