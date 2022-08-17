import java.util.Scanner;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author: Totsuka Tomofumi
 * @version: Level-2
 */
public class Duke {
    /**
     * Duke initialises with his personal logo and a welcome message before prompting for standard input
     * from the command line. Each input is remembered as a task by Duke and a list can be retrieved
     * if the user types "list". If the user types "bye", Duke terminates with a goodbye message.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //assume no more than 100 tasks
        String[] tasks = new String[100];
        int index = 0;
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
            } else if (response.equals("list")) {
                int order = 1;
                for (String str : tasks) {
                    if (str == null) break;
                    System.out.println(order++ + ". " + str);
                }
            } else {
                tasks[index++] = response;
                System.out.println("added: " + response);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
