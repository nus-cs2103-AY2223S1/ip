import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        // System.out.println("Enter username");

        // String userName = myObj.nextLine();  // Read user input
        // System.out.println("Username is: " + userName);  // Output user input
        System.out.println("Hello! I'm Duke \r\nWhat can I do for you? \r\nBye. Hope to see you again soon!");

    }
}
