import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static List<Task> storedTasks = new ArrayList<>();
    private static boolean isRunning = true;

    private static int parseTaskIndex(String str) throws DukeException {
        int index = Integer.parseInt(str) - 1;
        if (index < 0 || index >= storedTasks.size()) {
            throw new DukeException("Exception: Invalid task number.");
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (isRunning) {
            try {
                String[] inputLine = sc.nextLine().split(" ", 2);
                Command command = Command.strToEnum(inputLine[0]);
                switch (command) {
                case BYE:
                    System.out.println("Bye! Hope to see you again soon");
                    isRunning = false;
                    break;
                case LIST:
                    for (int i = 0; i < storedTasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, storedTasks.get(i));
                    }
                    System.out.println("That's all!");
                    break;
                case MARK:
                    try {
                        int index = parseTaskIndex(inputLine[1]);
                        Task task = storedTasks.get(index);
                        task.markAsDone();
                        System.out.println("Marked task " + (index + 1) + " as done!");
                        System.out.printf("%d. %s\n", index + 1, task);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Exception: Invalid command syntax.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Exception: Invalid task number.");
                    }
                    break;
                case UNMARK:
                    try {
                        int index = parseTaskIndex(inputLine[1]);
                        Task task = storedTasks.get(index);
                        task.markAsNotDone();
                        System.out.println("Marked task " + (index + 1) + " as not done!");
                        System.out.printf("%d. %s\n", index + 1, task);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Exception: Invalid command syntax.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Exception: Invalid task number.");
                    }
                    break;
                case DELETE:
                    try {
                        int index = parseTaskIndex(inputLine[1]);
                        Task task = storedTasks.get(index);
                        System.out.println("The following task is deleted:");
                        System.out.printf("%d. %s\n", index + 1, task);
                        storedTasks.remove(index);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Exception: Invalid command syntax.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Exception: Invalid task number.");
                    }
                    break;
                case TODO:
                    try {
                        String taskDescription = inputLine[1];
                        if (taskDescription.length() == 0) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                        Task task = new Todo(taskDescription);
                        storedTasks.add(task);
                        System.out.printf("Got it! I stored this task:\n" + task +
                                "\nNow you have %d tasks in the list.\n", storedTasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Exception: Empty task entry.");
                    }
                    break;
                case DEADLINE:
                    try {
                        String taskDescription = inputLine[1];
                        if (taskDescription.length() == 0) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                        String[] descAndDateTime = taskDescription.split("/by ", 2);
                        if (descAndDateTime.length != 2) {
                            throw new DukeException("Exception: No date-time.");
                        }
                        Task task = new Deadline(descAndDateTime[0], Parser.parseDateTime(descAndDateTime[1]));
                        storedTasks.add(task);
                        System.out.printf("Got it! I stored this task:\n" + task +
                                "\nNow you have %d tasks in the list.\n", storedTasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Exception: Empty task entry.");
                    }
                    break;
                case EVENT:
                    try {
                        String taskDescription = inputLine[1];
                        if (taskDescription.length() == 0) {
                            throw new DukeException("Exception: Empty task entry.");
                        }
                        String[] descAndDateTime = taskDescription.split("/at ", 2);
                        if (descAndDateTime.length != 2) {
                            throw new DukeException("Exception: No date-time.");
                        }
                        Task task = new Event(descAndDateTime[0], Parser.parseDateTime(descAndDateTime[1]));
                        storedTasks.add(task);
                        System.out.printf("Got it! I stored this task:\n" + task +
                                "\nNow you have %d tasks in the list.\n", storedTasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Exception: Empty task entry.");
                    }
                    break;
                default:
                    throw new DukeException("Exception: Unknown command.");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}

