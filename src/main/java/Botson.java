import java.util.Objects;
import java.util.Scanner;

public class Botson {
    public static void main(String[] args) {
        welcome();
    }

    /**
     * Duke Level 1. Greet, Echo, Exit
     * Echos the input and exit when user types "bye"
     **/
    public static void welcome() {
        System.out.println("Hello! I'm Botson");
        Scanner input = new Scanner(System.in);
        System.out.println("What can I help you with?");
        System.out.println("--------------------------");
        while (true) {
            String action = input.nextLine();  // Read user input
            if (Objects.equals(action, "bye")) {
                System.out.println("Goodbye! Hope to see you again soon!");
                System.out.println("--------------------------");
                break;
            }
            System.out.println(action);
            System.out.println("--------------------------");
        }
    }
}
