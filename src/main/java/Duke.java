import java.util.Scanner;  // Import the Scanner class
public class Duke {

    public static String receiveCommand() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String command = myObj.nextLine();  // Read user input
        return command;
    }

    public static void parseCommand(String command) {
        if(command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(command);
        }
        return;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String command = "";
        do {
            System.out.print("> ");
            command = receiveCommand();
            parseCommand(command);
        } while (!command.equals("bye"));
    }
}