import entities.Deadline;
import entities.Event;
import entities.Task;
import entities.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final Pattern COMMAND_REGEX = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: (/[a-zA-Z]+))?(?: ([^/]*))?$");
    private static final String LOGO = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n" +
            "\n";
    private final static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        customPrint(LOGO + "Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (sc.hasNextLine()) {
            if ((userInput = sc.nextLine()).equals("bye")) {
                break;
            }
            handleCommand(userInput);
        }
        customPrint("Bye. Hope to see you again soon!");
    }

    private static void customPrint(String s) {
        System.out.println("--------------------");
        System.out.println(s);
        System.out.println("--------------------");
    }

    private static void handleCommand(String s) {
        try {
            Matcher m = COMMAND_REGEX.matcher(s);
            m.find();
            String command = m.group(1);
            String value = m.group(2);
            String flag = m.group(3);
            String options = m.group(4);
            if (command == null) {
                customPrint("Please enter something!");
                return;
            }
            switch (command) {
                case "list":
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Here are the tasks in your list:\n");
                    int n = list.size();
                    for (int i = 0; i < n; i++) {
                        stringBuilder.append(String.format("%d. %s", i + 1, list.get(i)));
                        if (i != n - 1) {
                            stringBuilder.append("\n");
                        }
                    }
                    customPrint(stringBuilder.toString());
                    break;
                case "mark":
                    if (value == null) {
                        customPrint("Invalid list index!\nUsage: `mark 2`");
                        return;
                    }
                    try{
                        int number = Integer.parseInt(value);
                        Task item = list.get(number - 1);
                        item.setDone(true);
                        customPrint("Nice! I've marked this task as done:\n  " + item);
                    }
                    catch (NumberFormatException ex) {
                        customPrint("Invalid list index!\nUsage: `mark 2`");
                    }
                    break;
                case "unmark":
                    if (value == null) {
                        customPrint("Invalid list index!\nUsage: `unmark 2`");
                        return;
                    }
                    try{
                        int number = Integer.parseInt(value);
                        Task item = list.get(number - 1);
                        item.setDone(false);
                        customPrint("OK, I've marked this task as not done yet:\n  " + item);
                    }
                    catch (NumberFormatException ex) {
                        customPrint("Invalid list index!\nUsage: `unmark 2`");
                    }
                    break;
                case "todo":
                    if (value == null) {
                        customPrint("Please enter a task name!");
                        return;
                    }
                    Task todo = new Todo(value);
                    addToList(todo);
                    break;
                case "event":
                    if (value == null) {
                        customPrint("Please enter a task name!");
                        return;
                    }
                    if (!flag.equals("/at")) {
                        customPrint("Incorrect option flag!\nUsage:`event project meeting /at Mon 2pm`");
                        return;
                    }
                    if (options == null) {
                        customPrint("Please enter a time!");
                    }
                    Task event = new Event(value, options);
                    addToList(event);
                    break;
                case "deadline":
                    if (value == null) {
                        customPrint("Please enter a task name!");
                        return;
                    }
                    if (!flag.equals("/by")) {
                        customPrint("Incorrect option flag!\nUsage:`deadline return book /by Sunday`");
                        return;
                    }
                    if (options == null) {
                        customPrint("Please enter a deadline!");
                    }
                    Task deadline = new Deadline(value, options);
                    addToList(deadline);
                    break;
                default:
                    customPrint("Unknown command! Please try again");
                    return;
            }
        } catch (IllegalStateException ex) {
            // catch when no match found
            customPrint("Unknown command. Please try again!");
            return;
        }
    }

    private static void addToList(Task t) {
        list.add(t);
        int size = list.size();
        if (size == 1) {
            customPrint(String.format("Got it. I've added this task:\n  " +
                    t +
                    "\nNow you have %d task in the list.", size));
            return;
        }
        customPrint(String.format("Got it. I've added this task:\n  " +
                t +
                "\nNow you have %d tasks in the list.", size));
    }
}
