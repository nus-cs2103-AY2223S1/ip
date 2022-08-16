import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Create a scanner that reads user input.
        Scanner sc = new Scanner(System.in);

        // Greet the user.s
        System.out.println("Duke: Hello! I am Duke.");

        while (true) {
            String userInput = sc.next();

            if (userInput.equals("bye")) {
                System.out.println("Duke: Bye!");
                break;
            } else {
                System.out.println("Duke: " + userInput);
            }
        }
    }
}