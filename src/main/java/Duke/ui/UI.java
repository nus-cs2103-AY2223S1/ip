package Duke.ui;

import Duke.tasks.Task;

import java.util.Scanner;

public class UI {

    private static final Scanner sc = new Scanner(System.in);

    public  void showLineBreak() {
        System.out.println("    ____________________________________________________________\n");
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLineBreak();
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        showLineBreak();
    }

    public void goodbyeMessage() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void printListMessage() {
    }

    public boolean checkValid(String input) {
        String[] str = input.split(" ");
        return ((str.length != 1) &&  (str.length != 0));
    }

    public void addTaskMessage(Task task, int listSize) {
        System.out.println("    " + " Got it. I've added this task: ");
        System.out.println("       " + task);
        System.out.println("    " + " Now you have " + listSize + " tasks in the list.");
    }

    public void deleteTaskMessage(Task task, int listSize) {
        System.out.println("    " + " Got it. I've removed this task: ");
        System.out.println("       " + task);
        System.out.println("    " + " Now you have " + listSize + " tasks in the list.");
    }

    public void deleteAllMessage() {
        System.out.println("    " + " Got it. I've removed all tasks");
        System.out.println("    " + " Now you have 0 tasks in the list.");
    }

    public void showLoadingError() {
        System.out.println("    " + " Loading error!");
    }

    public void markTaskMessage(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }

    public void incorrectCommandMessage() {
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(.");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
