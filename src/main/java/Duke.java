import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What may I do for you?");

        while (true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            String in = input.nextLine();  // Read user input
            if (in.contains("bye")) {
                System.out.println("Bye. Hope to see you again soon!"); // Output the goodbye messages
                break;
            } else {
                System.out.println(in);  // Output user input
            }
        }
    }
}

