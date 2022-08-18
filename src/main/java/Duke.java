
import java.util.Scanner;
public class Duke {
    private static String WELCOME_MESSAGE =  "Hello! I'm Duke\n" + "What can I do for you?";
    private static String GOODBYE_MESSAGE =  "Bye. Hope to see you again soon!";


    /**
    * Note: You are strongly encouraged to customize the chatbot name,
    * command/display formats, and even the personality of the chatbot
    * to make your chatbot unique.
    */
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                break;
            }

            System.out.println(input);
        }

        scanner.close();
    }
}
