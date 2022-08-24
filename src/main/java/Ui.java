import java.util.Scanner;

public class Ui {

    private String DIVIDER = "__________________________________________________\n";
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public static void printInitialMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Yale\nWhat can I do for you?\n");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void printErrorMessage(DukeException e) {
        String errorMessage = DIVIDER + e.getMessage() + "\n" + DIVIDER;
    }

    public void printDeletedMessage(Task deletedTask) {
        String message = DIVIDER + "Noted. I've removed this task:\n  "
                + deletedTask
                + String.format("\nNow you have  tasks in the list.") + DIVIDER;
        System.out.println(message);
    }
}
