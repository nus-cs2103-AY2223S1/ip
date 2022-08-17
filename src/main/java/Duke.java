import java.sql.SQLOutput;
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
        System.out.println("What may I do for you?");

        ArrayList<Task> list = new ArrayList<>(); // list of user input
        while (true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            String in = input.nextLine();  // Read user input
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!"); // Output the goodbye messages
                break;
            } else if (in.equals("list")) {
                int count = 1;
                for (Task tasks : list) {
                    System.out.println(count + ". " + "[" + tasks.getStatusIcon() + "] " + tasks.getDescription());
                    count += 1;
                }
            } else if (in.contains("unmark")) {
                char n = in.charAt(7);
                int number = Character.getNumericValue(n) - 1;
                Task t = list.get(number);
                t.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.getDescription());
            } else if (in.contains("mark")) {
                char n = in.charAt(5);
                int number = Character.getNumericValue(n) - 1;
                Task t = list.get(number);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.getDescription());
            } else {
                Task t = new Task(in);
                list.add(t);
                System.out.println("added: " + in);  // Output user input
            }
        }
    }
}

