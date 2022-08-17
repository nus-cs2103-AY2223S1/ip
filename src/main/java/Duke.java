import exceptions.DukeException;

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

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static final Pattern commandParser =
            Pattern.compile("^([a-zA-Z]+)(?:\\s([^/]+))?(?:\\s/([a-zA-Z]+))?(?:\\s([^/]+))?$");

    private final TaskList taskList = new TaskList();

    private void handle(String command) throws DukeException {
        String function;
        String value;
        String flag;
        String additionalValue;

        try {
            Matcher matcher = Duke.commandParser.matcher(command);
            matcher.find();
            function = matcher.group(1);
            value = matcher.group(2);
            flag = matcher.group(3);
            additionalValue = matcher.group(4);
        } catch (IllegalStateException e) {
            throw new DukeException("This command does not exist!");
        }

        String message;
        switch(function) {
            case "list":
                message = this.taskList.toString();
                break;
            case "mark":
                message = this.taskList.markTask(value, true);
                break;
            case "unmark":
                message = this.taskList.markTask(value, false);
                break;
            case "todo":
                message = this.taskList.addToDo(value, flag, additionalValue);
                break;
            case "event":
                message = this.taskList.addEvent(value, flag, additionalValue);
                break;
            case "deadline":
                message = this.taskList.addDeadline(value, flag, additionalValue);
                break;
            default:
                message = "";
                break;
        }
        wrapWithLines(message);
    }

    private void initialise() {
        wrapWithLines("Hello from\n" + Duke.logo);

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                handle(command);
            } catch (DukeException e) {
                wrapWithLines("☹ OOPS!!! " + e.getMessage());
            }
        }
        bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }
}
