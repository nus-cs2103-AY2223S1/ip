import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Create a scanner that reads user input.
        Scanner sc = new Scanner(System.in);

        // Create a list to store tasks.
        List<String> tasks = new ArrayList<>();

        // Greet the user.
        System.out.println("Duke: Hello! I am Duke.");

        while (true) {
            String userInput = sc.next();

            if (userInput.equals("bye")) {
                System.out.println("Duke: Bye!");
                break;
            }

            if (userInput.equals("list")) {
                System.out.println("Duke: Here are your tasks.");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%3d: %s\n", i + 1, tasks.get(i));
                }
            } else {
                tasks.add(userInput);
                System.out.println("Duke: I have added " + userInput);
            }
        }
    }
}