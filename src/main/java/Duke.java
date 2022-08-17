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
        int size = 0;
        while (true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            String in = input.nextLine();  // Read user input
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!"); // Output the goodbye messages
                break;
            } else if (in.equals("list")) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task tasks : list) {
                    System.out.println(count + ". " + tasks.toString());
                    count += 1;
                }
            } else if (in.contains("unmark")) {
                char n = in.charAt(7);
                int number = Character.getNumericValue(n) - 1;
                Task t = list.get(number);
                t.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t.toString());
            } else if (in.contains("mark")) {
                char n = in.charAt(5);
                int number = Character.getNumericValue(n) - 1;
                Task t = list.get(number);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t.toString());
            } else if (in.contains("deadline")) {
                String deadLine = in.replaceFirst("deadline ", "");
                String[] aStr = deadLine.split("/by", 2);
                String desc = aStr[0];
                String by = aStr[1];
                Deadline d = new Deadline(desc, by);
                list.add(d);
                size += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + d.toString());
                System.out.println("Now you have " + size + " tasks in the list");
            } else if (in.contains("event")) {
                String event = in.replaceFirst("event ", "");
                String[] aStr = event.split("/at", 2);
                String desc = aStr[0];
                String by = aStr[1];
                Event e = new Event(desc, by);
                list.add(e);
                size += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + e.toString());
                System.out.println("Now you have " + size + " tasks in the list");
            } else {
                Todo t = new Todo(in);
                list.add(t);
                size += 1;
                System.out.println("Got it. I've added this task: ");  // Output user input
                System.out.println("  " + t.toString());
                System.out.println("Now you have " + size + " tasks in the list");
            }
        }
    }
}

