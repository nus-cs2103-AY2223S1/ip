import java.util.*;

public class Duke {

    private static Task[] addedTask = new Task[100];
    private static int addedTaskCount = 0;

    private static void addTask(Task task) {
        Duke.addedTask[Duke.addedTaskCount] = task;
        Duke.addedTaskCount++;
        System.out.println("Task added:");
        System.out.println(task);
        System.out.println("There are " + addedTaskCount + " tasks in your list.");
    }

    private static void listTask() {
        System.out.println("Listing your current tasks:");
        for (int i = 0; i < Duke.addedTask.length; ++i) {
            if (Duke.addedTask[i] == null) {
                return;
            }
            System.out.println((i + 1) + "." + Duke.addedTask[i]);
        }
    }

    private static void markTask(int index) {
        if (Duke.addedTask[index - 1] == null) {
            System.out.println("Task does not exist!");
            return;
        }
        Duke.addedTask[index - 1].mark();
    }

    private static void unmarkTask(int index) {
        if (Duke.addedTask[index - 1] == null) {
            System.out.println("Task does not exist!");
            return;
        }
        Duke.addedTask[index - 1].unmark();
    }

    private static void greet() {
        System.out.println("Hi, my name is Duke\nand it's power hour!");
        System.out.println("***********************");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] commandArgs = command.split(" ", 2);
            String keyword = commandArgs[0];
            switch (keyword) {
                case "bye":
                    System.out.println("Come again soon!");
                    System.exit(0);
                case "list":
                    Duke.listTask();
                    break;
                case "mark":
                    Duke.markTask(Integer.parseInt(commandArgs[1]));
                    break;
                case "unmark":
                    Duke.unmarkTask(Integer.parseInt(commandArgs[1]));
                    break;
                case "todo":
                    Todo todo = new Todo(commandArgs[1]);
                    Duke.addTask(todo);
                    break;
                case "deadline":
                    String[] deadlineDetails = commandArgs[1].split(" /by ");
                    if (deadlineDetails.length <= 1) {
                        System.out.println("Invalid deadline, try again.");
                    } else {
                        Duke.addTask(new Deadline(deadlineDetails[0],
                                deadlineDetails[1]));
                    }
                    break;
                case "event":
                    String[] eventDetails = commandArgs[1].split(" /at ");
                    if (eventDetails.length <= 1) {
                        System.out.println("Invalid event, try again.");
                    } else {
                        Duke.addTask(new Event(eventDetails[0],
                                eventDetails[1]));
                    }
                    break;
                default:
                    Duke.addTask(new Task(command));
            }
        }
    }

    public static void main(String[] args) {
        Duke.greet();
    }
}
