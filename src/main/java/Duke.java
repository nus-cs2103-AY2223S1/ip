import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        // Create arraylist to store given tasks
        ArrayList<Task> tasks = new ArrayList<Task>();

        // Read string input from user
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {

            if (command.contains("mark")) {
                // Isolate int from string input
                String numString = command.replace("mark", "")
                        .replace(" ", "");
                int num = Integer.parseInt(numString);
                tasks.get(num - 1).markAsDone();
                command = sc.nextLine();
                continue;
            }

            if (Objects.equals(command, "list")) {
                int count = 1;
                for (Task t : tasks) {
                    String s = Integer.toString(count);
                    System.out.println(s + ". " + t);
                    count++;
                }
                command = sc.nextLine();
                continue;
            }

            tasks.add(new Task(command));
            System.out.println("added: " + command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
