import java.util.Scanner;  // Import the Scanner class
public class Duke {

    private static String[] commands = new String[100];
    private static int i = 0;
    public static String receiveCommand() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String command = myObj.nextLine();  // Read user input
        return command;
    }

    public static void parseCommand(String command) {
        if(command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            for (int j = 0; j < i; j++) {
                System.out.println(j+1 + ": " + commands[j]);
            }
        } else {
            commands[i] = command;
            i += 1;
            System.out.println("added: " + command);
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