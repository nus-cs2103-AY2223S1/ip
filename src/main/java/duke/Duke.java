package duke;

import java.util.Scanner;

import duke.exception.InvalidTaskIndexException;
import duke.task.TaskList;

public class Duke {
    private static final TaskList taskList = new TaskList();

    private static void run(Scanner sc) {
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] commandSplit = command.split(" ", 2);
            switch (commandSplit[0]) {
            case "list":
                Duke.taskList.listTasks();
                break;
            case "mark":
                try {
                    int taskIndex = Integer.parseInt(commandSplit[1]);
                    Duke.taskList.markTaskAsDone(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println(commandSplit[1] + " is not a valid task index");
                } catch (InvalidTaskIndexException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    int taskIndex = Integer.parseInt(commandSplit[1]);
                    Duke.taskList.markTaskAsNotDone(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println(commandSplit[1] + " is not a valid task index");
                } catch (InvalidTaskIndexException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(commandSplit[1]);
                    Duke.taskList.deletetask(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println(commandSplit[1] + " is not a valid task index");
                } catch (InvalidTaskIndexException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                if (commandSplit.length > 1) {
                    Duke.taskList.addTask(TaskList.TaskType.TODO, commandSplit[1]);
                } else {
                    System.out.println("The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                if (commandSplit.length > 1) {
                    Duke.taskList.addTask(TaskList.TaskType.DEADLINE, commandSplit[1]);
                } else {
                    System.out.println("The description of a deadline cannot be empty.");
                }
                break;
            case "event":
                if (commandSplit.length > 1) {
                    Duke.taskList.addTask(TaskList.TaskType.EVENT, commandSplit[1]);
                } else {
                    System.out.println("The description of a deadline cannot be empty.");
                }
                break;
            default:
                System.out.println("Unknown command");
                break;
            }
            System.out.println();
            command = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");

        Scanner myScanner = new Scanner(System.in);
        Duke.run(myScanner);

        System.out.println("Bye. Hope to see you again soon!");
    }
}
