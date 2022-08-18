import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void echo(String echoCommand) {
        System.out.println("--------------------------------------------------");
        System.out.println(echoCommand);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        echo("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<String> tasklist = new ArrayList<String>();

        String command = "";
        while (true) {
            Scanner userInput = new Scanner(System.in);// Create a Scanner object
            command = userInput.nextLine(); // Read user input
            if ("bye".equals(command)) {
                echo("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(command)) {
                String taskRecords = "";
                for (int i = 0; i < tasklist.size(); i++) {
                    String taskRecord="";
                    if (i == tasklist.size() - 1) {
                        taskRecord = String.format("%d. %s", i + 1, tasklist.get(i));
                    } else {
                        taskRecord = String.format("%d. %s\n", i + 1, tasklist.get(i));
                    }
                    taskRecords += taskRecord;
                }
                echo(taskRecords);
            } else {
                echo(command);
                tasklist.add(command);
            }
        }

    }
}
