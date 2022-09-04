package duke.ui;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {

    Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }
    public void greetingsPrint() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void fileErrorPrint() {
        System.out.println("Throwing error in file");
    }

    public void fileNotFoundPrint() {
        System.out.println("File not found error");
    }

    public void generalErrorPrint() {
        System.out.println("An error occurred.");
    }

    public void dukeEmptyPrint() {
        System.out.println("Nth to read form duke.txt");
    }

    private String emptyPrint(String str) {
        return(String.format("☹ OOPS!!! The description of a %s cannot be empty.", str));
    }

    public void emptyMarkPrint() {
        System.out.println(emptyPrint("mark"));
    }

    public void emptyUnmarkPrint() {
        System.out.println(emptyPrint("unmark"));
    }

    public void emptyDeletePrint() {
        System.out.println(emptyPrint("delete"));
    }

    public void emptyToDoPrint() {
        System.out.println(emptyPrint("ToDo"));
    }

    public void emptyEventPrint() {
        System.out.println(emptyPrint("event"));
    }

    public void emptyDeadlinePrint() {
        System.out.println(emptyPrint("deadline"));
    }

    private String countTasks(int no) {
        return(String.format("Now you have %d tasks in the list.", no));
    }

    public void deletePrint(Task task, int count) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(countTasks(count));
    }

    public void addTaskPrint(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(countTasks(count));
    }

    public void addUnknownPrint() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void emptyListPrint() {
        System.out.println("☹ OOPS!!! I'm sorry, but cannot print empty list");
    }

    public void byePrintPrint() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void newDirectoryPrint() {
        System.out.println("hi, made new directory");
    }

    public void markTaskPrint(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTaskPrint(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public String readInput() {
        return sc.nextLine();
    }
}
