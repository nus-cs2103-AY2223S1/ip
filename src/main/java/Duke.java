import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

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
            System.out.println(userCommand);
            System.out.println("----------------------");
            userCommand = enterInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon ^^!");




    }
}
