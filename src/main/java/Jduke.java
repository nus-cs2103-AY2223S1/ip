import java.util.ArrayList;
import java.util.Scanner;

public class Jduke {
    private static final String GREETING = "|  Welcome to JDuke -- Version 1.0\n" +
            "|  What can I do for you?";
    private static final String PROMPT = "jduke> ";
    private static final String GOODBYE = "|  Goodbye";
    private static final String TODO_FORMAT = "todo <description>";
    private static final String EVENT_FORMAT = "event <description> /at <timing>";
    private static final String DEADLINE_FORMAT = "deadline <description> /by <timing>";
    private static final String MARK_FORMAT = "mark <integer>";
    private static final String UNMARK_FORMAT = "unmark <integer>";
    private static final String DELETE_FORMAT = "delete <integer>";
    public enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static Command handleCommand(String input) throws JdukeException {
        Command mainCmd;
        try {
            mainCmd = Command.valueOf(input.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JdukeException("cannot understand command");
        }
        return mainCmd;
    }
    public static void printLastTask() {
        System.out.printf(
                "|  added task:%n|    %s%n|  %d%s in list%n",
                tasks.get(tasks.size() - 1),
                tasks.size(),
                (tasks.size() == 1 ? " task" : " tasks")
        );
    }
    public static void addTodo(String input) throws JdukeException {
        if (!input.toLowerCase().matches("todo [^ ](.*)")) {
            throw new JdukeException("invalid TODO format", TODO_FORMAT);
        }
        tasks.add(new ToDo(input.split(" ", 2)[1]));
        printLastTask();
    }

    public static void addDeadline(String input) throws JdukeException {
        if (!input.toLowerCase().matches("deadline [^ ](.*) /by (.*)")) {
            throw new JdukeException("invalid DEADLINE format", DEADLINE_FORMAT);
        }
        String details = input.split(" ", 2)[1];
        String[] deadlineParams = details.split(" /by ", 2);
        tasks.add(new Deadline(deadlineParams[0], deadlineParams[1]));
        printLastTask();
    }
    public static void addEvent(String input) throws JdukeException {
        if (!input.toLowerCase().matches("event [^ ](.*) /at (.*)")) {
            throw new JdukeException("invalid EVENT format", EVENT_FORMAT);
        }
        String details = input.split(" ", 2)[1];
        String[] eventParams = details.split(" /at ", 2);
        tasks.add(new Event(eventParams[0], eventParams[1]));
        printLastTask();
    }
    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("|  no tasks found");
        }
        for (int pos = 0; pos < tasks.size(); pos++) {
            System.out.printf("%d ==> %s%n", pos + 1, tasks.get(pos));
        }
    }
    public static int handleTaskPos(String input) throws JdukeException {
        String[] inputArr = input.split(" ");
        if (!input.matches("(.*) ([0-9]+)") || inputArr.length > 2) {
            switch (inputArr[0].toLowerCase()) {
                case "mark":
                    throw new JdukeException("invalid MARK format", MARK_FORMAT);
                case "unmark":
                    throw new JdukeException("invalid UNMARK format", UNMARK_FORMAT);
                case "delete":
                    throw new JdukeException("invalid DELETE format", DELETE_FORMAT);
            }
        }
        int pos = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        if (pos < 0 || tasks.size() <= pos) {
            throw new JdukeException(
                    "invalid task number",
                    String.format("max index is %d", tasks.size())
            );
        }
        return pos;
    }
    public static void unmarkTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        tasks.get(pos).markAsUndone();
        System.out.printf("|  unmarked task:%n|    %s%n", tasks.get(pos));
    }
    public static void markTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        tasks.get(pos).markAsDone();
        System.out.printf("|  marked task:%n|    %s%n", tasks.get(pos));
    }
    public static void deleteTask(String input) throws JdukeException {
        int pos = handleTaskPos(input);
        System.out.printf(
                "|  deleted task:%n|    %s%n|  %d%s in list%n",
                tasks.get(pos),
                (tasks.size() - 1),
                (tasks.size() == 2 ? " task" : " tasks")
                );
        tasks.remove(pos);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        System.out.printf("%n%s", PROMPT);
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            try {
                Command mainCmd = handleCommand(input);
                switch (mainCmd) {
                    case LIST:
                        listTasks();
                        break;
                    case MARK:
                        markTask(input);
                        break;
                    case UNMARK:
                        unmarkTask(input);
                        break;
                    case TODO:
                        addTodo(input);
                        break;
                    case DEADLINE:
                        addDeadline(input);
                        break;
                    case EVENT:
                        addEvent(input);
                        break;
                    case DELETE:
                        deleteTask(input);
                        break;
                }
            } catch (JdukeException e) {
                System.out.printf("|  Error:%n%s%n", e.getMessage());
            } finally {
                System.out.printf("%n%s", PROMPT);
                input = scanner.nextLine().trim();
            }
        }
        System.out.printf("%s%n", GOODBYE);
        scanner.close();
    }
}
