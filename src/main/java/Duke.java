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

            else if (userInput.startsWith("todo ")) {
                String toDoName = userInput.substring(5).strip();
                tasks.add(new ToDo(toDoName));
                System.out.printf("Duke: I have added the to-do %s.\n", toDoName);
            }

            // e.g. userInput is "event supernova @ friday night"
            else if (userInput.startsWith("event ")) {
                // TODO: Ensure that the string contains a "@" and a time range is specified.
                String eventName = userInput.substring(6).split("@ ")[0].strip();
                String eventTimeRange = userInput.split("@ ")[1].strip();
                tasks.add(new Event(eventName, eventTimeRange));
                System.out.printf("Duke: I have added the event %s.\n", eventName);
            }

            else if (userInput.startsWith("deadline ")) {
                // TODO: Ensure that the string contains a "@" and a due date is specified.
                String deadlineName = userInput.substring(9).split("@ ")[0].strip();
                String deadlineDueDate = userInput.split("@ ")[1].strip();
                tasks.add(new Deadline(deadlineName, deadlineDueDate));

                System.out.printf("Duke: I have added the deadline %s.\n", deadlineName);
            }

            else {
                System.out.println("Duke: I don't understand!");
            }
        }
    }
}