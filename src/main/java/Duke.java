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
        while (scanner.hasNext()) {
            String input = scanner.nextLine().toLowerCase();
            String inputLowerCase = input.toLowerCase();
            switch (inputLowerCase) {
                case "bye":
                    exitDuke();
                    return;
                case "list":
                    printMessages(taskList.getAllTasks().toArray(new String[0]));
                    break;
                default:
                    taskList.addTask(input);
                    printMessages(new String[]{"added: " + input});
            }
        }
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
