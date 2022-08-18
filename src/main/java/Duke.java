import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        //Echo echo = new Echo();
        String input = userInput.nextLine();
        TaskList tasks = new TaskList();
        //echo.echoInput(userInput);
        while (!input.equals("bye")) {

            if (input.equals("list")) {
                tasks.listTasks();
                input = userInput.nextLine();
                continue;
            }
            if (input.matches("mark +\\d+") || input.matches("unmark +\\d+")) {
                String[] inputParts = input.split(" ", 2);
                if (inputParts[0].equals("mark")) {
                    tasks.mark(Integer.parseInt(inputParts[1]));
                    input = userInput.nextLine();
                }
                if (inputParts[0].equals("unmark")) {
                    tasks.unmark(Integer.parseInt(inputParts[1]));
                    input = userInput.nextLine();
                }
            } else {
                tasks.addTask(input);
                input = userInput.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
