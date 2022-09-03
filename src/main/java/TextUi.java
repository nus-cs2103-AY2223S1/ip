import java.util.Scanner;

public class TextUi {
    private Scanner scanner;

    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out the given text with dividers to the console
     *
     * @param text The specified text to be printed to the console
     */
    public void printTextWithDivider(String text) {
        final String divider = "-".repeat(100) + "\n";
        System.out.println(divider + text + divider);
    }

    public void showLoadingError() {
        String str = "Unable to load tasks from file!\n";
        printTextWithDivider(str);
    }

    public void showWelcomeMessage() {
         String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         String greetingMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

         String welcomeMsg = logo + greetingMsg;

         printTextWithDivider(welcomeMsg);
    }

    public void showGoodByeMessage() {
        String goodbyeMsg = "Bye. Hope to see you again soon!\n";
        printTextWithDivider(goodbyeMsg);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showAddTaskMessage(Task task, TaskList tasks) {
        String message =  "Got it. I've added this task:\n" +
                "  " + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.\n";

        printTextWithDivider(message);
    }

    public void showMarkTaskMessage(Task task) {
        String message = "Nice! I've marked this as done:\n" +
                    task + "\n";
        printTextWithDivider(message);
    }

    public void showUnmarkTaskMessage(Task task) {
        String message = "Ok, I've marked this task as not done yet:\n" +
                    task + "\n";
        printTextWithDivider(message);
    }

    public void showRemoveTaskMessage(Task task, TaskList tasks) {
        String message = "Noted. I've removed this task:\n" +
                "  " + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.\n";
        printTextWithDivider(message);
    }
}
