import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Create a scanner that reads user input.
        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        // Create a list to store tasks.
        List<Task> tasks = new ArrayList<>();

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
            }

            else if (userInput.startsWith("mark ")) {
                // TODO: Ensure that an index is provided (and not a word).
                // TODO: Ensure that the index provided is reasonable.
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                tasks.get(index).setComplete(true);
                System.out.println("Duke: Task marked as complete.");
                System.out.println(tasks.get(index));
            }

            else if (userInput.startsWith("unmark ")) {
                // TODO: Ensure that an index is provided (and not a word).
                // TODO: Ensure that the index provided is reasonable.
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                tasks.get(index).setComplete(false);
                System.out.println("Duke: Task marked as incomplete.");
                System.out.println(tasks.get(index));
            }

            else {
                tasks.add(new Task(userInput));
                System.out.println("Duke: I have added " + userInput);
            }
        }
    }
}