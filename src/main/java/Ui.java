import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    public void showWelcome() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public void showLoadingError() {
        System.out.println("There was an error loading your file. Starting a new list...\n");
    }

    public void showLine() {
        System.out.println("-------------------------------");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAdd(Task t, int len) {
        System.out.println("Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + len
                + taskString(len) + "in the list.");
    }

    public void showDelete(Task t, int len) {
        String notice = "Noted. I've removed this task:\n";
        String desc = t.toString() + "\n";
        String tasksLeft = "Now you have " + len + taskString(len) + "in the list.";
        System.out.println(notice + desc + tasksLeft);
    }


    public void showMark(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + t);
    }

    public void showUnmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
    }

    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    private String taskString(int len) {
        if (len <= 1) {
            return " task ";
        } else {
            return " tasks ";
        }
    }
}
