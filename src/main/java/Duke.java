/**
 * Project done by Hong Jin
 * Student ID: A0239059W
 */
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String initText = "Hello! I'm Duke\n    What can I do for you?";
        String endText = "Bye bye! Hope to see you again soon!";

        Scanner scan = new Scanner(System.in);
        printMsg(initText);

        while (true) {
            System.out.println("Say something: ");
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            }
            printMsg(input);
        }
        printMsg(endText);
    }

    /**
     * Class method to print message with horizontal line.
     * @param str
     */
    public static void printMsg(String str) {
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + str);
        System.out.println("  ____________________________________________________________");
    }
}
