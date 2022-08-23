import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "\t____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Formats Duke's messages by adding horizontal line dividers and indentation.
     *
     * @param str Duke's message to be printed out
     */
    public static void prettyPrint(String str) {
        String[] messages = str.split("\n");
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println("\t  " + message);
        }
        System.out.println(DIVIDER);
    }

    public static void greet(String numberOfTasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = String.join("\n", logo,
                String.format("Hello, I'm Duke! %s", numberOfTasks),
                "What can I do for you?");
        prettyPrint(welcomeMessage);
    }
}
