import java.util.Scanner;

public class Duke {

    private static final String EXIT_COMMAND = "bye";

    public static void processInputs() {

        while (true) {

            Scanner sc = new Scanner(System.in);

            String input = sc.nextLine();

            // Check for exit for command
            if (input.equals(EXIT_COMMAND)) {
                sc.close();
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }

            System.out.println(input);
            System.out.println();
        }
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");

        // Process user inputs
        processInputs();

    }
}
