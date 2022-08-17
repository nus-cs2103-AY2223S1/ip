import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        final String GREETING = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        final String GOODBYE = "Bye. Hope to see you again soon!\n";

        System.out.println("Hello from\n" + LOGO);

        printTextWithDivider(GREETING);

        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            // Quit when user enters "bye"
            if (command.equals("bye")) {
                printTextWithDivider(GOODBYE);
                break;
            }

            executeCommand(command);
        }

    }

    /**
     * Prints out the given text with dividers to the console
     *
     * @param text The specified text to be printed to the console
     */
    public static void printTextWithDivider(String text) {
        final String divider = "-".repeat(80) + "\n";
        System.out.println(divider + text + divider);
    }

    /**
     * Prints out the command entered by users
     *
     * @param command The specified command
     */
    public static void executeCommand(String command) {
        printTextWithDivider(command+"\n");
    }
}
