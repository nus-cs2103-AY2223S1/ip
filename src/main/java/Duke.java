import java.util.Objects;
import java.util.Scanner;


/**
 * Duke is a basic chat-bot that echoes whatever the user inputs.
 *
 * @author Chi Song Yi Amadeus
 * @version Level-1
 * @since 17-08-2022
 */
public class Duke {

    /**
    * Main method initializes welcome message, and then calls echo method.
    *
    * @param args unused.
    */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }

     /**
     * This method handles the echo loop. It echoes whatever the user inputs, but ends once
     * the user enters "bye".
     *
     */
     public static void echo() {
        String initMessage =
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";

         Scanner userInput = new Scanner(System.in);
         System.out.println(initMessage);

         String input = userInput.nextLine();
         while (!Objects.equals(input.toLowerCase(), "bye")) {
             System.out.printf(
                     "    ____________________________________________________________\n" +
                     "     %s\n" +
                     "    ____________________________________________________________%n", input);
             input = userInput.nextLine();
         }

         System.out.println(
                 "    ____________________________________________________________\n" +
                 "     Bye. Hope to see you again soon!\n" +
                 "    ____________________________________________________________\n");
     }
}
