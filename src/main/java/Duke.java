import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    private static final List<Task> INPUT_HISTORY = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(LOGO);
        System.out.println(GREETING);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            }

            if (userInput.equals("list")) {
                printInputHistory();
                continue;
            }

            String[] split = userInput.split(" ");
            if (split.length == 2 && isNumeric(split[1]) && split[0].equals("mark")) {
                Task task = INPUT_HISTORY.get(Integer.parseInt(split[1]) - 1);
                markTaskAsDone(task);
                continue;
            }

            if (split.length == 2 && isNumeric(split[1]) && split[0].equals("unmark")) {
                Task task = INPUT_HISTORY.get(Integer.parseInt(split[1]) - 1);
                markTaskAsUndone(task);
                continue;
            }

            addToInputHistory(userInput);
        }
    }

    private static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }

    private static void markTaskAsDone(Task task) {
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void markTaskAsUndone(Task task) {
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    private static void addToInputHistory(String input) {
        INPUT_HISTORY.add(new Task(input));
        System.out.println(String.format("added: %s", input));
    }

    private static void printInputHistory() {
        for (int i = 0; i < INPUT_HISTORY.size(); i++) {
            System.out.println(String.format("%d. %s", 1 + i, INPUT_HISTORY.get(i).toString()));
        }
    }
}
