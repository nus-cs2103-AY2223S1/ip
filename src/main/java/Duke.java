import java.util.Scanner;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        List<Task> l = new ArrayList<Task>();

        while (true) {
            String line = input.nextLine();
            System.out.println("____________________________________________________________");
            if ("bye".equals(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if ("list".equals(line)) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : l) {
                    System.out.println(String.format("%d.%s %s", count, t.getStatusIcon(),
                            t.getDescription()));
                    count += 1;
                }
            }
            else if (line.contains("mark") && !(line.contains("unmark"))) {
                int number = Integer.parseInt(line.substring(5));
                Task t = l.get(number - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
            }

            else if (line.contains("unmark")) {
                int number = Integer.parseInt(line.substring(7));
                Task t = l.get(number - 1);
                t.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
            }

            else {
                System.out.println(String.format("added: %s", line));
                l.add(new Task(line));
            }
            System.out.println("____________________________________________________________");
        }
    }
}
