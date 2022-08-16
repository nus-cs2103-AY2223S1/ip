/**
 * @author Marciano Renzo William
 */

import java.util.Scanner;

/**
 * This is the Main Class that contains the Main method.
 */
public class Duke {
    private final static String underline = "____________________________________________________________";
    private final static String indentation = "  ";

    /**
     * Private constructor of Duke.
     */
    private Duke() {
    }

    public void run() {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Hello! I'm Duke\n" +
                indentation + "What can I do for you?");
        System.out.println(indentation + underline);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String chat = sc.nextLine();
            if (chat.equals("bye")) {
                sc.close();
                System.out.println(indentation + underline);
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                System.out.println(indentation + underline);
                break;
            } else {
                System.out.println(indentation + underline);
                System.out.println(indentation + chat);
                System.out.println(indentation + underline);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
