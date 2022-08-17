import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void makeLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2015");
        }
        System.out.println();
    }

    private static void wrapWithLines(String message) {
        makeLine();
        System.out.println(message);
        makeLine();
    }

    private static void echo(String command) {
        System.out.println(command);
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static final Pattern commandParser = Pattern.compile("^([a-zA-Z]+)(?:\\s([^/]+))?(?:\\s/([a-zA-Z]+))?(?:\\s([^/]+))?$");

    private void initialise() {
        wrapWithLines("Hello from\n" + Duke.logo);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        TaskList taskList = new TaskList();
        while (!command.equals("bye")) {
            Matcher matcher = Duke.commandParser.matcher(command);
            boolean isValidInput = matcher.find();
            String function = matcher.group(1);
            String value = matcher.group(2);
            String flag = matcher.group(3);
            String additionalValue = matcher.group(4);
            String message;
            switch(function) {
                case "list":
                    message = taskList.toString();
                    break;
                case "mark":
                    message = taskList.markTask(Integer.parseInt(value));
                    break;
                case "unmark":
                    message = taskList.unmarkTask(Integer.parseInt(value));
                    break;
                case "todo":
                    message = taskList.addToDo(value);
                    break;
                case "event":
                    message = taskList.addEvent(value, additionalValue);
                    break;
                case "deadline":
                    message = taskList.addDeadline(value, additionalValue);
                    break;
                default:
                    message = taskList.addTask(command);
                    break;
            }
            wrapWithLines(message);
            command = sc.nextLine();
        }
        bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }
}
