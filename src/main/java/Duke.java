import java.util.Scanner;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author: Totsuka Tomofumi
 * @version: Level-1
 */
public class Duke {
    /**
     * Duke initialises with his personal logo and a welcome message before prompting for standard input
     * from the command line. If the user types "bye", Duke terminates with a goodbye message.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            } else {
                System.out.println(response);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
