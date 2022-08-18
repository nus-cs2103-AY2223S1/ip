import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        //Echo echo = new Echo();
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        TaskList tasks = new TaskList();
        //echo.echoInput(userInput);
        while (!input.equals("bye")) {
            if (input.equals("list")){
                tasks.listTasks();
                input = userInput.nextLine();
            } else {
                tasks.addTask(input);
                input = userInput.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
