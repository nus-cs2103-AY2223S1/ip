package duke;

import java.util.Scanner;

public class Ui {
    static final String WELCOME_MSG = "Hello! I'm Duke.\nWhat can I do for you?";
    static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getCommand() {
        return sc.nextLine();
    }

    private void printMessage(String message) {
        String line = "____________________________________________________________";
        String res = line + "\n" + message + "\n" +line;
        res = res.replaceAll("(?m)^", "\t");
        System.out.println(res);
    }

    public void printWelcome() {
        printMessage(WELCOME_MSG);
    }

    public void printGoodbye() {
        printMessage(GOODBYE_MSG);
    }

    public void printTaskList(String taskList) {

        printMessage("Here are the tasks in your list:\n" + taskList);
    }

    public void printMatchingTaskList(String taskList) {
        printMessage("Here are the matching tasks in your list:\n" + taskList);
    }

    public void printAddTaskReply(String taskDesc, int totalTask) {
        printMessage("Got it. I've added this task:\n\t" + taskDesc
                + "\nNow you have " + totalTask + " tasks in the list.");
    }

    public void printMarkTaskReply(String taskDesc) {
        printMessage("Nice! I've marked this task as done:\n\t" + taskDesc);
    }

    public void printUnmarkTaskReply(String taskDesc) {
        printMessage("OK, I've marked this task as not done yet:\n\t" + taskDesc);
    }

    public void printDeleteTaskReply(String taskDesc, int totalTask) {
        printMessage("Noted. I've removed this task:\n\t" + taskDesc +
                "\nNow you have " + totalTask + " tasks in the list.");
    }

    public void printException(Exception e) {
        printMessage("OOPS! " + e.getMessage());
    }
}
