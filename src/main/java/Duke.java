import java.util.Scanner;
public class Duke {
    private static String DIVIDER = "-------------------------------------\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        handler();
    }

    // greet method contains the greeting message
    private static void greet() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + message + DIVIDER);
    }

    // handler method handles user input and outputs accordingly
    private static void handler() {
        String command;
        String message;
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();

        switch(command) {
            case "bye":
                message = "Bye. Hope to see you again soon!\n";
                System.out.println(DIVIDER + message + DIVIDER);
                break;
            default:
                message = command + "\n";
                System.out.println(DIVIDER + message + DIVIDER);
                handler();
        }
    }

}
