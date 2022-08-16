import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Create a scanner that reads user input.
        Scanner sc = new Scanner(System.in);

        // This is a test comment.

        // This is a second test comment.

        // Greet the user.
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