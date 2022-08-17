import java.util.*;

public class Duke {

    private static ArrayList<Task> addedTask = new ArrayList<>(100);

    private static int getSize() {
        return Duke.addedTask.size();
    }

    private static void reportSize() {
        if (Duke.getSize() == 1) {
            System.out.println("There is " + Duke.getSize() + " task in your list.");
        } else {
            System.out.println("There are " + Duke.getSize() + " tasks in your list.");
        }
    }

    private static void addTask(Task task) {
        Duke.addedTask.add(task);
        System.out.println("Task added:");
        System.out.println(task);
        reportSize();
    }

    private static void deleteTask(int index) throws TaskNotFoundException {
        if (index > Duke.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        System.out.println("Task successfully removed:");
        System.out.println(Duke.addedTask.remove(index - 1));
        reportSize();
    }

    private static void listTask() {
        System.out.println("Listing your current tasks:");
        for (int i = 0; i < Duke.getSize(); ++i) {
            System.out.println((i + 1) + "." + Duke.addedTask.get(i));
        }
    }

    private static void markTask(int index) throws TaskNotFoundException {
        if (index > Duke.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Duke.addedTask.get(index - 1).mark();
    }

    private static void unmarkTask(int index) throws TaskNotFoundException {
        if (index > Duke.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Duke.addedTask.get(index - 1).unmark();
    }

    private static void processCommand(String command)
            throws DukeException {
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
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Duke.markTask(Integer.parseInt(commandArgs[1]));
                break;
            case "unmark":
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Duke.unmarkTask(Integer.parseInt(commandArgs[1]));
                break;
            case "delete":
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Duke.deleteTask(Integer.parseInt(commandArgs[1]));
                break;
            case "todo":
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Todo todo = new Todo(commandArgs[1]);
                Duke.addTask(todo);
                break;
            case "deadline":
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                String[] deadlineDetails = commandArgs[1].split(" /by ");
                if (deadlineDetails.length == 1) {
                    throw new IllegalCommandException(commandArgs[0]);
                }
                Duke.addTask(new Deadline(deadlineDetails[0],
                        deadlineDetails[1]));
                break;
            case "event":
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                String[] eventDetails = commandArgs[1].split(" /at ");
                if (eventDetails.length <= 1) {
                    throw new IllegalCommandException(commandArgs[0]);
                }
                Duke.addTask(new Event(eventDetails[0],
                        eventDetails[1]));
                break;
            default:
                throw new CommandNotFoundException(command);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hi, my name is Duke\nand it's power hour!");
        System.out.println("***********************");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                Duke.processCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
