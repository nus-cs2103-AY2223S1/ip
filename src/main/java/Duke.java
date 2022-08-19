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
            command = in.nextLine().trim();
            commandList = command.split(" ",2);
            command = (Helper.multipleVariable(commandList[0])) ? commandList[0] : command;


            switch (command) {
                case "bye": {
                    Interface.bye();
                    lastCommandOrNot = true;
                    break;
                }
                case "mark":
                case "unmark": {
                    taskIndex = Helper.strToInt(commandList[1]) - 1;
                    task = taskList.get(taskIndex);
                    switch (command) {
                        case "mark": {
                            Interface.mark(task);
                            break;
                        }
                        case "unmark": {
                            Interface.unmark(task);
                            break;
                        }
                    }
                    break;
                }
                case "list": {
                    Interface.list(taskList);
                    break;
                }
                case "todo": {
                    taskList.add(Interface.addToDo(commandList[1]));
                    break;
                }
                case "deadline": {
                    taskList.add(Interface.addDeadline(commandList[1]));
                    break;
                }
                case "event": {
                    taskList.add(Interface.addEvent(commandList[1]));
                    break;
                }
                default:
                    System.out.println("No such command available.");
            }
        }
    }

}
