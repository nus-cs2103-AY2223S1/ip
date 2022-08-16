import java.util.Scanner;
import java.util.ArrayList;
public class Jduke {
    public enum TaskType {
        TODO, DEADLINE, EVENT
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
        TaskType type = TaskType.valueOf(inputArr[0].toUpperCase());
        if (inputArr.length != 2) {
            throw new JdukeException(
                    "|  cannot find description\n" +
                            "|    task type: " + type
            );
        }
        String details = inputArr[1];
        switch (type) {
            case TODO:
                tasks.add(new ToDo(details));
                break;
            case DEADLINE:
            case EVENT:
                String[] detailsArr = details.split("/");
                if (detailsArr.length != 2) {
                    throw new JdukeException(
                            "|  cannot find timing\n" +
                                    "|    task type: " + type +
                                    "\n|  " + type.toString().toLowerCase() +
                                    " <description> " +
                                    (type.equals(TaskType.DEADLINE) ? "/by" : "/at") +
                                    " <timing>"
                    );
                }
                String description = details.split("/")[0];
                String timing = details.split("/")[1].split(" ", 2)[1];
                String preposition = details.split("/")[1].split(" ", 2)[0];
                if (description.equals("")) {
                    throw new JdukeException(
                            "|  cannot find description\n" +
                                    "|    task type: " + type
                    );
                };
                if (type.equals(TaskType.DEADLINE)) {
                    if (!preposition.equalsIgnoreCase("by")) {
                        throw new JdukeException(
                                "|  invalid preposition:\n" +
                                        "|    provided: " + preposition +
                                        "\n|  " + type.toString().toLowerCase() + " <description> /by <timing>"
                        );
                    }
                    tasks.add(new Deadline(description, timing));
                } else {
                    if (!preposition.equalsIgnoreCase("at")) {
                        throw new JdukeException(
                                "|  invalid preposition:\n" +
                                        "|    provided: " + preposition +
                                        "\n|  " + type.toString().toLowerCase() + " <description> /at <timing>"
                        );
                    }
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
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            String mainCmd = input.split(" ", 2)[0].toLowerCase();
            try {
                switch (mainCmd) {
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                    case "unmark":
                        markTask(input);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(input);
                        break;
                    case "delete":
                        deleteTask(input);
                        break;
                    default:
                        unknownCommand(input);
                }
            } catch (JdukeException e) {
                System.out.println("|  Error:\n" + e.getMessage());
            } finally {
                System.out.print("\njduke> ");
                input = scanner.nextLine().trim();
            }
        }
        System.out.println("|  Goodbye");
        scanner.close();
    }
}
