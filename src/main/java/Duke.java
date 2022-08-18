import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Integer.decode;
import static java.lang.Integer.parseInt;

public class Duke {
    // Special commands
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private static final String MARK_REGEX = "mark [0-9]+";
    private static final String UNMARK_REGEX = "unmark [0-9]+";
    private static final String TODO_REGEX = "todo [a-z[A-Z][0-9]_ :-]*";
    private static final String DEADLINE_REGEX = "deadline [a-z[A-Z][0-9]_ ]* /by [a-z[A-Z][0-9]_ :-]*";
    private static final String EVENT_REGEX = "event [a-z[A-Z][0-9]_ ]* /at [a-z[A-Z][0-9]_ :-]*";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t-------------------------------");
        System.out.println("\tHello, I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-------------------------------");

        while (true) {
            Scanner s = new Scanner(System.in);
            String command = s.nextLine().trim();
            System.out.println("\t-------------------------------");
            if (command.equals(BYE)) {
                System.out.println("\tBye! Hope to see you again");
                break;
            } else if (command.equals(LIST)) {
                Task.listTasks();
            } else if (Pattern.matches(MARK_REGEX, command)) {
                String temp = command.replaceAll("[^0-9]", ""); // extracting the digits
                int taskNumber = parseInt(temp);
                Task.markAsDone(taskNumber - 1);
            } else if (Pattern.matches(UNMARK_REGEX, command)) {
                String temp = command.replaceAll("[^0-9]", ""); // extracting the digits
                int taskNumber = parseInt(temp);
                Task.markAsNotDone(taskNumber - 1);
            } else if (Pattern.matches(TODO_REGEX, command)) {
                String desc = command.substring(TODO.length()); // Ignore the word "todo"
                Task.add(new ToDo(desc));
            } else if (Pattern.matches(DEADLINE_REGEX, command)) {
                String temp = command.substring(DEADLINE.length());
                String[] details = temp.split("/by");
                details[0] = details[0].trim(); // description
                details[1] = details[1].trim(); // deadline
                Task.add(new Deadline(details[0], details[1]));
            } else if (Pattern.matches(EVENT_REGEX, command)){
                String temp = command.substring(EVENT.length());
                String[] details = temp.split("/at");
                details[0] = details[0].trim(); // description
                details[1] = details[1].trim(); // timing
                Task.add(new Event(details[0], details[1]));
            } else {
                System.out.println("\tSorry! Command not recognized");
            }
            System.out.println("\t-------------------------------");
        }
        System.out.println("\t-------------------------------");
    }
}

