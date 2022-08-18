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
        Task task;
        String command;
        String[] commandList;
        Integer taskIndex;

        Interface.greet();
        while (!lastCommandOrNot) {
            command = in.nextLine();
            commandList = command.split(" ");
            command = (commandList[0].equals("mark")) || (commandList[0].equals("unmark")) ? commandList[0] : command;

            switch (command) {
                case "bye" -> {
                    Interface.bye();
                    lastCommandOrNot = true;
                }
                case "mark" -> {
                    taskIndex = Helper.strToInt(commandList[1]) - 1;
                    task = taskList.get(taskIndex);
                    Interface.mark(task);
                }
                case "unmark" -> {
                    taskIndex = Helper.strToInt(commandList[1]) - 1;
                    task = taskList.get(taskIndex);
                    Interface.unmark(task);
                }
                case "list" -> Interface.list(taskList);
                default -> taskList.add(Interface.add(command));
            }
        }
    }

}
