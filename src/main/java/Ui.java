import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\n" + "What can I do for you?");
    }

    public void showExit() {
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit);
    }

    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void showList(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    public void showAddMessage(Task task, int numTask) {
        String message = "Got it. I've added this task:\n  " + task.toString() +
                "\nNow you have " + numTask + " tasks in the list.";
        System.out.println(message);
    }

    public void showDeleteMessage(Task task, int numTask) {
        String message = "Noted. I've removed this task:\n  " + task.toString() +
                "\nNow you have " + numTask + " tasks in the list.";
        System.out.println(message);
    }

    public void showMarkedMessage(Task task) {
        String message = "Nice! I've marked this task as done\n  " + task.toString();
        System.out.println(message);
    }

    public void showUnmarkedMessage(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task.toString();
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Unable to load data");
    }

    public void showError(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

}
