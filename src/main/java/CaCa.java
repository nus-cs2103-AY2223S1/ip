import java.util.Scanner;

/**
 * CaCa is a personal assistant chatbot that helps users manage and track your things.
 * It greets user, echos inputs by user and exits when user types bye.
 */
// ASCII text banner below created and adapted from
// https://manytools.org/hacker-tools/ascii-banner/
// with the following settings:
// Banner text: CaCa, Font: Big, Horizontal spacing: Normal, Vertical spacing: Normal.
public class CaCa {
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
            }
            System.out.println(input + "\n" + line);
        }
    }
}
