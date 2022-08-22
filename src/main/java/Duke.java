import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        TaskList myTasks = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            switch(userInput.split(" ")[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    myTasks.listTasks();
                    break;
                case "mark":
                    myTasks.markTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    myTasks.saveTasks();
                    break;
                case "unmark":
                    myTasks.unmarkTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    myTasks.saveTasks();
                    break;
                case "todo":
                    myTasks.addTask(new Todo(userInput.substring(5)));
                    myTasks.saveTasks();
                    break;
                case "deadline":
                    myTasks.addTask(new Deadline(
                        userInput.substring(9).split("/by")[0], LocalDate.parse(
                        userInput.split("/by ")[1])));
                    myTasks.saveTasks();
                    break;
                case "event":
                    myTasks.addTask(new Event(
                        userInput.substring(6).split("/at")[0], LocalDate.parse(
                        userInput.split("/at ")[1])));
                    myTasks.saveTasks();
                    break;
                case "delete":
                    myTasks.removeTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    myTasks.saveTasks();
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
    }
}
