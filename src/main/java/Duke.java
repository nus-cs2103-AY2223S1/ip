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
     * Array used to store user inputs as a list.
     */
    static String[] wordList = new String[100];

    /**
     * Counter to keep track of the number of items in the list
     */
    static int count = 0;

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
        String initMessage =
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     I can store a to-do list for you!\n" +
                "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
                "     Start entering your tasks!\n" +
                "    ____________________________________________________________";

        System.out.println(initMessage);
        list();
    }

    /**
     * list collects user inputs into wordList, and updates count.
     * Loop ends when user enters "bye".
     *
     */
     public static void list() {
         Scanner userInput = new Scanner(System.in);
         String input = userInput.nextLine();

         while (!Objects.equals(input.toLowerCase(), "bye")) {

             if (Objects.equals(input.toLowerCase(), "list")) {
                 // readList function called to display list contents
                readList();

             } else {
                 // wordList and count updated here, and shown to the user that item has been added
                 wordList[count] = input;
                 count++;

                 System.out.printf(
                         "    ____________________________________________________________\n" +
                         "     added: %s\n" +
                         "    ____________________________________________________________%n", input);
             }

             input = userInput.nextLine();
         }

         System.out.println(
                 "    ____________________________________________________________\n" +
                 "     Bye. Hope to see you again soon!\n" +
                 "    ____________________________________________________________\n");
     }

    /**
     * Function prints all items in the list chronologically entered and numbered from 1 upwards.
     *
     */
    public static void readList() {
        System.out.println("    ____________________________________________________________\n");

        for (int i = 1; i <= count; i++){
            System.out.printf("%d. %s%n", i, wordList[i-1]);
        }

        System.out.println("    ____________________________________________________________\n");
     }
}
