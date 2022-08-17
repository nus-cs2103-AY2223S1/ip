import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Duke {
    // Special commands
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";


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

        Task.add(null);

        while (true) {
            Scanner s = new Scanner(System.in);
            String command = s.nextLine().trim();
            System.out.println("\t-------------------------------");
            if (command.equals(BYE)) {
                System.out.println("\tBye! Hope to see you again");
                break;
            } else if (command.equals(LIST)) {
                Task.listTasks();
            } else if (Pattern.matches("mark [0-9]+", command)) {
                String temp = command.replaceAll("[^0-9]", ""); // extracting the digits
                int taskNumber = parseInt(temp);
                Task.markAsDone(taskNumber);
            } else if (Pattern.matches("unmark [0-9]+", command)) {
                String temp = command.replaceAll("[^0-9]", ""); // extracting the digits
                int taskNumber = parseInt(temp);
                Task.markAsNotDone(taskNumber);
            } else {
                System.out.println("\tadded: " + command);
                Task.add(new Task(command));
            }
            System.out.println("\t-------------------------------");
        }
        System.out.println("\t-------------------------------");
    }
}

