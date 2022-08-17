import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * CaCa is a personal assistant chatbot that helps users manage and track your things.
 */
// ASCII text banner below created and adapted from
// https://manytools.org/hacker-tools/ascii-banner/
// with the following settings:
// Banner text: CaCa, Font: Big, Horizontal spacing: Normal, Vertical spacing: Normal.
public class CaCa {

    /**
     * A class-level array to store all user inputs.
     */
    private static List<String> items = new ArrayList<>();

    /**
     * The program greets user, reads and stores user input,
     * displays items when user inputs list and exits when user inputs bye.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";

        String logo = "   _____       _____      \n"
                + "  / ____|     / ____|     \n"
                + " | |     __ _| |     __ _ \n"
                + " | |    / _` | |    / _` |\n"
                + " | |___| (_| | |___| (_| |\n"
                + "  \\_____\\__,_|\\_____\\__,_|\n\n";
        String greeting = "Hello! I'm CaCa.\n"
                + "What can I do for you?\n";
        System.out.println(line + logo + greeting + line);

        // Solution below adapted from https://www.w3schools.com/java/java_user_input.asp
        Scanner sc = new Scanner(System.in); // Creates a Scanner Object.

        while (true) {
            String input = sc.nextLine(); // Reads user input.
            System.out.print(line);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    String item = items.get(i);
                    System.out.printf("%d. %s%n", i + 1, item);
                    if (i == items.size() - 1) {
                        System.out.print(line);
                    }
                }
            } else {
                items.add(input);
                System.out.println("added: " + input + "\n" + line);
            }
        }
    }
}
