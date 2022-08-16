import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE
    }
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static String getTaskDetails(int pos) {
        return "[" +
                tasks.get(pos).getType() +
                "][" +
                tasks.get(pos).getStatus() +
                "] " +
                tasks.get(pos).getDescription();
    }
    public static void addTask(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length != 2) {
            throw new DukeException(
                    "The description of " +
                            inputArr[0] +
                            " cannot be empty!"
            );
        }
        String type = inputArr[0];
        String details = inputArr[1];
        switch (type) {
            case "todo":
                tasks.add(new ToDo(details));
                System.out.println(
                        "Got it. I've added this task:\n" +
                                getTaskDetails(tasks.size() - 1) +
                                "\nNow you have " +
                                tasks.size() +
                                (tasks.size() == 1 ? " task" : " tasks") +
                                " in the list."
                );
                break;
            case "deadline":
            case "event":
                String[] detailsArr = details.split("/");
                if (detailsArr.length != 2) {
                    throw new DukeException("Timing required for " + type + "!");
                }
                String description = details.split("/")[0];
                String timing = details.split("/")[1].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    tasks.add(new Deadline(description, timing));
                } else {
                    tasks.add(new Event(description, timing));
                }
                System.out.println(
                        "Got it. I've added this task:\n" +
                                getTaskDetails(tasks.size() - 1) +
                                "\nNow you have " +
                                tasks.size() +
                                (tasks.size() == 1 ? " task" : " tasks") +
                                " in the list."
                );
        }
    }
    public static void unknownCommand(String input) throws DukeException {
        throw new DukeException(
                "I'm unable to do '" +
                         input +
                        "' at the moment. :("
        );
    }
    public static void listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("No tasks found.");
        }
        for (int pos = 0; pos < tasks.size(); pos++) {
            System.out.println(pos + 1 + ". " + getTaskDetails(pos));
        }
    }
    public static int handleTaskPos(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        int pos;
        if (inputArr.length == 1) {
            throw new DukeException("Provide a task number to mark");
        } else if (inputArr.length > 2) {
            throw new DukeException("Provide a valid task number!");
        }
        try {
            pos = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Provide a numeric task number!");
        }
        if (pos < 0) {
            throw new DukeException("Provide a valid number!");
        }
        if (tasks.size() <= pos) {
            throw new DukeException("No such task.");
        }
        return pos;
    }
    public static void markTask(String input) throws DukeException {
        int pos = handleTaskPos(input);
        switch (input.split(" ")[0]) {
            case "mark":
                tasks.get(pos).markAsDone();
                System.out.println(
                        "Nice! I've marked this task as done\n" +
                                getTaskDetails(pos)
                );
                break;
            case "unmark":
                tasks.get(pos).markAsUndone();
                System.out.println(
                        "Nice! I've marked this task as undone\n" +
                                getTaskDetails(pos)
                );
        }
    }
    public static void deleteTask(String input) throws DukeException {
        int pos = handleTaskPos(input);
        System.out.println(
                "OK. I've removed this task:\n" +
                        getTaskDetails(pos) +
                        "\nNow you have " +
                        (tasks.size() - 1) +
                        (tasks.size() == 2 ? " task" : " tasks") +
                        " in the list."
        );
        tasks.remove(pos);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello! I'm Duke.\nWhat can I do for you?\n>> ");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            Command mainCmd = Command.valueOf(input.split(" ", 2)[0].toUpperCase());
            try {
                switch (mainCmd) {
                    case LIST:
                        listTasks();
                        break;
                    case MARK:
                    case UNMARK:
                        markTask(input);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        addTask(input);
                        break;
                    case DELETE:
                        deleteTask(input);
                        break;
                    default:
                        unknownCommand(input);
                    }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.print("\n>> ");
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
