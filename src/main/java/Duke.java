import java.util.Scanner;

public class Duke {

    public static void echo(String echoCommand) {
        System.out.println("--------------------------------------------------");
        System.out.println(echoCommand);
        System.out.println("--------------------------------------------------");
    }
    public static void main(String[] args) {
        echo("Hello! I'm Duke\nWhat can I do for you?");

        String command = "";
        while (true) {
            Scanner userInput = new Scanner(System.in);// Create a Scanner object
            command = userInput.nextLine(); // Read user input
            if ( !"bye".equals(command)) {
                echo(command);
            } else {
                break;
            }
        }
        echo("Bye. Hope to see you again soon!");
    }
}
