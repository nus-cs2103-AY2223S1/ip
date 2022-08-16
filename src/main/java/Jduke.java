import java.util.Scanner;
import java.util.ArrayList;
public class Jduke {
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
    public static void addTask(String input) throws JdukeException {
        String[] inputArr = input.split(" ", 2);
        String type = inputArr[0];
        if (inputArr.length != 2) {
            throw new JdukeException(
                    "|  cannot find description\n" +
                            "|    task type: " +
                            type
            );
        }
        String details = inputArr[1];
        switch (type) {
            case "todo":
                tasks.add(new ToDo(details));
                break;
            case "deadline":
            case "event":
                String[] detailsArr = details.split("/");
                if (detailsArr.length != 2) {
                    throw new JdukeException(
                            "|  cannot find timing\n" +
                                    "|    task type: " + type +
                                    "\n|  " + type +
                                    (type.equals("deadline") ? " /by" : " /at") +
                                    " <timing> "
                    );
                }
                String description = details.split("/")[0];
                String timing = details.split("/")[1].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    tasks.add(new Deadline(description, timing));
                } else {
                    tasks.add(new Event(description, timing));
                }
                break;
        }
        System.out.println(
                "|  added task:\n" +
                        "|    " +
                        getTaskDetails(tasks.size() - 1) +
                        "\n|  " +
                        tasks.size() +
                        (tasks.size() == 1 ? " task" : " tasks") +
                        " in list"
        );
    }
    public static void unknownCommand(String input) throws JdukeException {
        throw new JdukeException(
                "|  cannot understand command\n" +
                         "|    command: " + input
        );
    }
    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("|  no tasks found");
        }
        for (int pos = 0; pos < tasks.size(); pos++) {
            System.out.println(pos + 1 + " ==> " + getTaskDetails(pos));
        }
    }
    public static int handleTaskPos(String input) throws JdukeException {
        String[] inputArr = input.split(" ");
        int pos;
        if (inputArr.length == 1) {
            throw new JdukeException(
                    "|  cannot find number\n" +
                            "|    command: " + input +
                            "\n|  " + inputArr[0] + " <integer>"
            );
        } else if (inputArr.length > 2) {
            throw new JdukeException(
                    "|  invalid task number\n" +
                            "|    provided: " + inputArr[1] +
                            "\n|  " + inputArr[0] + " <integer>"
            );
        }
        try {
            pos = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JdukeException(
                    "|  incompatible number type: integer required\n" +
                            "|    provided: " + inputArr[1] +
                            "\n|  " + inputArr[0] + " <integer>"
            );
        }
        if (pos < 0) {
            throw new JdukeException(
                    "|  invalid task number: positive integer required\n" +
                            "|    provided: " + inputArr[1] +
                            "\n|  " + inputArr[0] + " <integer>"
            );
        }
        if (tasks.size() <= pos) {
            throw new JdukeException(
                    "|  missing task\n" +
                            "|    provided: " + inputArr[1] +
                            "\n|  " + inputArr[0] + " <integer>"
            );
        }
        return pos;
    }
    public static void markTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        String type = input.split(" ")[0];
        switch (type) {
            case "mark":
                tasks.get(pos).markAsDone();
                break;
            case "unmark":
                tasks.get(pos).markAsUndone();
                break;
        }
        System.out.println(
                "|  " + type + "ed task:\n" +
                        "|    " +
                        getTaskDetails(pos)
        );
    }
    public static void deleteTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        System.out.println(
                "|  deleted task:\n" +
                        "|    " +
                        getTaskDetails(pos) +
                        "\n|  " +
                        (tasks.size() - 1) +
                        (tasks.size() == 2 ? " task" : " tasks") +
                        " in list"
        );
        tasks.remove(pos);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                "|  Welcome to JDuke -- Version 1.0\n" +
                    "|  What can I do for you?\n\n" +
                        "jduke> "
        );
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
            } catch (JdukeException e) {
                System.out.println("|  Error:\n" + e.getMessage());
            } finally {
                System.out.print("\njduke> ");
                input = scanner.nextLine();
            }
        }
        System.out.println("|  Goodbye");
        scanner.close();
    }
}
