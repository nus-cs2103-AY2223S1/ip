import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        run();
    }

    /**
     * Main interface function:
     * 1. Greetings on opening.
     * 2. bye: to exit.
     * 3. list: to print task list.
     * 4. Else: to add task.
     */
    public static void run() {
        Scanner in = new Scanner(System.in);
        boolean lastCommandOrNot = false;
        String command;

        Interface.greet();
        while (!lastCommandOrNot) {
            command = in.nextLine();
            switch (command) {
                case "bye":
                    Interface.bye();
                    lastCommandOrNot = true;
                    break;
                case "list":
                    Interface.list(taskList);
                    break;
                default:
                    taskList.add(Interface.add(command));
            }
        }
    }

}
