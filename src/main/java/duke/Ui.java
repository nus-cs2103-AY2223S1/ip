package duke;

import java.util.Scanner;

public class Ui {
    private static String botName = "DIGITAL DADDY";
    private static String emoji = "\uD83E\uDD16";
    private static String lineSeparator =
            "____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void botReply(String message) {
        String reply = String.format("%s\n%s %s %s \n%s \n%s", lineSeparator, emoji,
                botName, emoji, message, lineSeparator);
        System.out.println(reply);
    }

    public void welcome() {
        botReply("Hello! I'm " + botName + "\nWhat can I do for you?");
    }

    public void goodbye() {
        botReply("Bye. Hope to see you again soon!");
        this.sc.close();
    }

    public void showTasks(TaskList tasks) {
        botReply(tasks.toString());
    }

    public void showError(String message) {
        botReply("\uD83E\uDD22" + " OOPS!!! " + message);
    }

    public void showLoadingError(DukeException e) {
        botReply(e.getMessage());
    }
}
