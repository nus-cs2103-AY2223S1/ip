import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * KKBot is a chatbot.
 * It currently functions like a to-do list.
 * It stores tasks and displays these stored tasks to users when asked.
 */
public class KKBot {
    // create a class level array to store all user-input tasks
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Welcome message and header
        String divider = "____________________________________________________________\n";
        String logo = " __   __   __   __  _____\n"
                    + "|  | /  / |  | /  /|  __  \\\n"
                    + "|  |/  /  |  |/  / | |__|  |\n"
                    + "|     /   |     /  |      /\n"
                    + "|     \\   |     \\  |  __  \\\n"
                    + "|  |\\  \\  |  |\\  \\ | |__|  |\n"
                    + "|__| \\__\\ |__| \\__\\|______/\n";
        String welcomeMessage = "Hello! I'm KKBot! \n"
                + "What can I do for you?\n";
        System.out.println(divider + logo + welcomeMessage + divider);

        // Add scanner in for user input (create scanner object)
        Scanner userText = new Scanner(System.in);
        // scanner function:
        // if input is bye, send out closing message,
        // if input is list, shows all stored task
        // else, stores user-input tasks into the above list
        while (true) {
            String input = userText.nextLine();
            System.out.println(divider);
            if (input.equals("bye") || input.equals("Bye") || input.equals("BYE")) {
                System.out.println("KKBot signing off. Goodbye!\n" + divider);
                break;
            } else if (input.equals("list") || input.equals("List") || input.equals("LIST")) {
                // internal check if there are any tasks to do
                if (tasks.isEmpty()) {
                    System.out.println("Good job! There's no tasks at hand!\n" + divider);
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        String task = tasks.get(i);
                        String index = String.valueOf(i + 1);
                        if (i == tasks.size() - 1) {
                            System.out.println(index + ". " + task + "\n" + divider);
                        } else {
                            System.out.println(index + ". " + task + "\n");
                        }
                    }
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input + "\n" + divider);
            }
        }
    }
}
