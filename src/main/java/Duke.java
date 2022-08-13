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

    /**
     * Greeting Method for ChatBot.
     *
     * Prints Greetings Message.
     */
    private static void greetings() {
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
        String command = sc.next();
        String description = sc.nextLine();


        while (!command.toLowerCase().equals("bye")) {
            if (command.toLowerCase().equals("list")) {
                // Print List
                System.out.println("\n-----------------------------------------");
                System.out.println("Here are the tasks in your list");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                System.out.println("-----------------------------------------\n");
            }
            else if (command.toLowerCase().equals("mark")) {
                int index = Integer.parseInt(description.substring(1)) - 1;

                if (0 <= index && index < tasks.size()) {
                    Task task = tasks.get(index);
                    task.markAsDone();

                    System.out.println("\n-----------------------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + task);
                    System.out.println("-----------------------------------------\n");
                } else {
                    System.out.println("\n-----------------------------------------");
                    System.out.println("Boo... Task don't exist :(");
                    System.out.println("-----------------------------------------\n");
                }
            }
            else if (command.toLowerCase().equals("unmark")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                if (0 <= index && index < tasks.size()) {
                    Task task = tasks.get(index);
                    task.markAsUndone();

                    System.out.println("\n-----------------------------------------");
                    System.out.println("Ok! I've marked this task as not done yet:");
                    System.out.println("    " + task);
                    System.out.println("-----------------------------------------\n");
                } else {
                    System.out.println("\n-----------------------------------------");
                    System.out.println("Boo... Task don't exist :(");
                    System.out.println("-----------------------------------------\n");
                }
            }
            else if (command.toLowerCase().equals("todo")) {
                ToDo todo = new ToDo(description.substring(1));
                tasks.add(todo);

                System.out.println("\n-----------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------\n");
            }
            else if (command.toLowerCase().equals("deadline")) {
                String[] details = description.split(" /by ");
                Deadline deadline = new Deadline(details[0], details[1]);
                tasks.add(deadline);

                System.out.println("\n-----------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------\n");
            }
            else if (command.toLowerCase().equals("event")) {
                String[] details = description.split(" /at ");
                Event event = new Event(details[0], details[1]);
                tasks.add(event);

                System.out.println("\n-----------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------\n");
            }
            else {
                System.out.println("\n-----------------------------------------");
                System.out.println("Sorry! I'm incapable :( Please add more functionalities to me");
                System.out.println("-----------------------------------------\n");
            }
            command = sc.next();
            description = sc.nextLine();
        }

        farewell();
    }
}