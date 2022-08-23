import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tasks.*;
import exceptions.*;
import utils.DeadlineParser;

public class Duke {

    private static final TaskList TASK_LIST = new TaskList();
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: (.*))?$");

    public void handle(String command) {
        String action;
        String desc;
        String time;

        try {
            Matcher matcher = COMMAND_PATTERN.matcher(command);
            if (matcher.find()) {
                action = matcher.group(1);
                desc = matcher.group(2);
                time = matcher.group(4);
            } else {
                throw new InvalidCommandException();
            }
            switch (action) {
                case "list":
                    TASK_LIST.printList();
                    break;
                case "todo":
                    TASK_LIST.addToDo(desc);
                    break;
                case "event":
                    System.out.println(time);
                    TASK_LIST.addEvent(desc, time);
                    break;
                case "deadline":
                    TASK_LIST.addDeadline(desc, time);
                    break;
                case "delete":
                    TASK_LIST.delete(desc);
                    break;
                case "done":
                    TASK_LIST.markDone(desc);
                    break;
                case "before":
                    TASK_LIST.printDeadline(time);
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            System.out.println(e);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter the correct due date format d/mm/yyyy [HHmm]");
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            duke.handle(command);
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
