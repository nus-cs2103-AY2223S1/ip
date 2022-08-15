import java.util.Scanner;

public class Duke {
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static final String DEADLINE_INDICATOR = " /by ";
    private static final String EVENT_INDICATOR = " /at ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final TaskList taskList = new TaskList();
    public static void main(String[] args) {
        greetUser();
        runDuke();
    }

    public static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();
            String inputCommand = input.indexOf(" ") == -1 ?
                    input.toLowerCase() : input.toLowerCase().substring(0, input.indexOf(" "));
            switch (inputCommand) {
                case "bye":
                    exitDuke();
                    return;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(Integer.parseInt(input.split(" ")[1]) - 1);
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(input.split(" ")[1]) - 1);
                    break;
                case "todo":
                    addTodo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                default:
                    unknownCommand();
                    break;
            }
        }
    }

    public static void printAddedTask(Task task) {
        printMessages(new String[] {
                "Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size())
        });
    }

    public static void addTodo(String input) {
        String taskDescription = input.substring("todo ".length());
        Task taskAdded = taskList.addTask(new ToDo(taskDescription));
        printAddedTask(taskAdded);
    }

    public static void addDeadline(String input) {
        String[] textTokens = input.substring("deadline ".length()).split(DEADLINE_INDICATOR, 2);
        Task taskAdded = taskList.addTask((new Deadline(textTokens[0], textTokens[1])));
        printAddedTask(taskAdded);
    }

    public static void addEvent(String input) {
        String[] textTokens = input.substring("event ".length()).split(EVENT_INDICATOR, 2);
        Task taskAdded = taskList.addTask((new Event(textTokens[0], textTokens[1])));
        printAddedTask(taskAdded);
    }
    public static void markTask(int taskIndex) {
        Task markedTask = taskList.markTask(taskIndex);
        printMessages(new String[]{"Nice! I've marked this task as done:", markedTask.toString()});
    }

    public static void unmarkTask(int taskIndex) {
        Task unmarkedTask = taskList.unmarkTask(taskIndex);
        printMessages(new String[]{"Ok, I've marked this task as not done yet:", unmarkedTask.toString()});
    }

    public static void listTasks() {
        printMessages(taskList.getAllTasks().toArray(new String[0]));
    }

    public static void printMessages(String[] messages) {
        System.out.println(LINE_DIVIDER);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void greetUser() {
        System.out.println(LOGO);
        printMessages(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }

    public static void unknownCommand() {
        printMessages(new String[]{"Unknown command provided"});
    }

    public static void exitDuke() {
        printMessages(new String[]{"Bye. Hope to see you again soon!"});
    }
}
