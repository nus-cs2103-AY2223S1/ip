import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Duke class that instantiates instances of duke
 *
 * Duke is a chatbot that performs different actions
 * based on commands provided by user
 */
public class Duke {
    // Name of Bot
    private static final String BOT_NAME = "Duke";
    // Bot Name Logo
    private static final String BOT_LOGO = " ____        _        \n"
                                        + "|  _ \\ _   _| | _ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * Greeting Method for ChatBot.
     *
     * Prints Greetings Message.
     */
    private static void greetings() {
        System.out.println(BOT_LOGO);
        System.out.printf("Hello! I'm the Magical ChatBot, %s!%n", BOT_NAME);
        System.out.println("What can I help you with today?\n");
    }

    /**
     * Greeting Method for ChatBot.
     *
     * Prints Farewell Message.
     */
    private static void farewell() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println(BOT_LOGO);
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Method to get Reply from user
     *
     * @param sc Scanner to scan for input.
     *
     * @return A string containing user's reply
     */
    private static String getReply(Scanner sc) {
        System.out.print("Please enter your command: ");
        String reply = sc.nextLine();

        return reply;
    }

    /**
     * Method to generate result given user's reply
     *
     * Prints the result.
     *
     * @param reply The user's reply.
     */
    private static void getResult(String reply) {
        System.out.println("\n-----------------------------------------");
        System.out.println(reply);
        System.out.println("-----------------------------------------\n");
    }

    /**
     * The main Method that runs the Duke programme
     *
     * @param args arguments (if any).
     */
    public static void main(String[] args) {
        greetings();

        // Initialise variables
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String reply = getReply(sc);


        while (!reply.toLowerCase().equals("bye")) {
            String[] words = reply.split(" ");
            if (reply.toLowerCase().equals("list")) {
                // Print List
                System.out.println("\n-----------------------------------------");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                System.out.println("-----------------------------------------\n");
            } else if (words[0].toLowerCase().equals("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                if (0 <= index && index < tasks.size()) {
                    Task task = tasks.get(index);
                    task.markAsDone();

                    System.out.println("\n-----------------------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + task);
                    System.out.println("-----------------------------------------\n");
                } else {
                    getResult("Boo... Task don't exist :(");
                }
            } else if (words[0].toLowerCase().equals("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                if (0 <= index && index < tasks.size()) {
                    Task task = tasks.get(index);
                    task.markAsUndone();

                    System.out.println("\n-----------------------------------------");
                    System.out.println("Ok! I've marked this task as not done yet:");
                    System.out.println("    " + task);
                    System.out.println("-----------------------------------------\n");
                } else {
                    getResult("Boo... Task don't exist :(");
                }
            } else {
                // Add Reply to List
                getResult("added: " + reply);
                tasks.add(new Task(reply));
            }

            reply = getReply(sc);
        }

        farewell();
    }
}