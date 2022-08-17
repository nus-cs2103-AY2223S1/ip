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

        ArrayList<String> list = new ArrayList<>(); // list of user input
        while (true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            String in = input.nextLine();  // Read user input
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!"); // Output the goodbye messages
                break;
            } else if (in.equals("list")) {
                int count = 1;
                for (String tasks: list) {
                    System.out.println(count + ". " + tasks);
                    count += 1;
                }
            } else {
                list.add(in);
                System.out.println("added: " + in);  // Output user input
            }
        }
    }
}

