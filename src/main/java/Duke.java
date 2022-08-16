import java.util.Scanner;

public class Duke {
    private static String DIVIDER = "\t____________________________________________________________";

    /**
     * Formats Duke's messages by adding horizontal line dividers and indentation.
     * @param str Duke's message to be printed out
     */
    private static void prettyPrint(String str) {
        String[] messages = str.split("\n");
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println("\t  " + message);
        }
        System.out.println(DIVIDER);
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = String.join("\n", logo,
                "Hello! I'm Duke", "What can I do for you?");
        prettyPrint(welcomeMessage);
    }

    private static void goodbye() {
        String farewellMessage = "Bye. Hope to see you again soon!";
        prettyPrint(farewellMessage);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cmd;
        greet();
        // Echoes user's input until the user types 'bye', for which the program exits
        while(!(cmd = sc.nextLine()).equals("bye")) {
            prettyPrint(cmd);
        }
        goodbye();
    }
}
