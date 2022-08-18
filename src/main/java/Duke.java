import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tasks.*;
import exceptions.*;


public class Duke {

    private static final TaskList taskList = new TaskList();

    public void handle(String command) {
        String action;
        String desc;
        String time;

        try {
            Pattern pattern = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: ([^/]*))?$");
            Matcher matcher = pattern.matcher(command);
            matcher.find();
            action = matcher.group(1);
            desc = matcher.group(2);
            time = matcher.group(4);

            switch (action) {
                case "list":
                    taskList.printList();
                    break;
                case "todo":
                    taskList.addToDo(desc);
                    break;
                case "event":
                    taskList.addEvent(desc, time);
                    break;
                case "deadline":
                    taskList.addDeadline(desc, time);
                    break;
                case "delete":
                    taskList.delete(desc);
                    break;
                case "done":
                    taskList.markDone(desc);
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            System.out.println(e);
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
