import java.util.Scanner;

public class Duke {
    private static final String name = "Duke";

    public static void main(String[] args) {
        greet();
        processUserCommand();
    }

    private static void greet() {
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
    }

    private static void processUserCommand() {
        Scanner myScanner = new Scanner(System.in);
        String userCommand = myScanner.nextLine();

        if (userCommand.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        }

        System.out.println(userCommand);
        processUserCommand();
    }

}
