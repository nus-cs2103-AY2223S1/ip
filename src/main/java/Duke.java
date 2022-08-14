import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
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
                default:
                    addTask(input);
                    break;
            }
        }
    }

    public static void addTask(String task) {
        taskList.addTask(task);
        printMessages(new String[]{"added: " + task});
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

    public static void exitDuke() {
        printMessages(new String[]{"Bye. Hope to see you again soon!"});
    }
}
