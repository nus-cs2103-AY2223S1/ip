import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Create an Array to store the text the user has entered of size 100
        String[] storedTextArray = new String[100];
        // Create a count to track the number of items the user has stored
        int count = 0;

        String userCommand = "";

        // Creates a scanner to accept input
        Scanner enterInput = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        userCommand = enterInput.nextLine();

        // When user does not type bye, ask for input and print out the input
        while (!userCommand.equals("bye")) {
            System.out.println("----------------------");
            // If user types list, output all the text being stored
            if (userCommand.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + storedTextArray[i]);
                }
            } else {
                // For anything else the user types, store into the array
                storedTextArray[count] = userCommand;
                count += 1;
                System.out.println("added:" + userCommand);
            }
            System.out.println("----------------------");
            userCommand = enterInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon ^^!");
    }
}
