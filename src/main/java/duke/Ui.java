package duke;

import java.util.Scanner;

public class Ui {
    private static String BOT_NAME = "DIGITAL DADDY";
    private static String EMOJI = "\uD83E\uDD16";
    private static String LINE_SEPARATOR =
            "____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void reply(String message) {
        String botReply = String.format("%s\n%s %s %s \n%s \n%s", LINE_SEPARATOR, EMOJI,
                BOT_NAME, EMOJI, message, LINE_SEPARATOR);
        System.out.println(botReply);
    }

    public void welcome() {
        reply("Hello! I'm " + BOT_NAME + "\nWhat can I do for you?");
    }

    public void sayGoodbye() {
        reply("Bye. Hope to see you again soon!");
        this.sc.close();
    }

    public void showTasks(TaskList tasks) {
        reply(tasks.toString());
    }

    public void showError(String message) {
        reply("\uD83E\uDD22" + " OOPS!!! " + message);
    }

    public void showLoadingError(DukeException e) {
        reply(e.getMessage());
    }
}
