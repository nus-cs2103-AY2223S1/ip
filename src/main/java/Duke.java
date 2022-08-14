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

    private static final List<Task> TASKS = new ArrayList<>();

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
                printTasks();
                continue;
            }

            String[] split = userInput.split(" ");
            if (split.length == 2 && isNumeric(split[1]) && split[0].equals("mark")) {
                Task task = TASKS.get(Integer.parseInt(split[1]) - 1);
                markTaskAsDone(task);
                continue;
            }

            if (split.length == 2 && isNumeric(split[1]) && split[0].equals("unmark")) {
                Task task = TASKS.get(Integer.parseInt(split[1]) - 1);
                markTaskAsUndone(task);
                continue;
            }

            if (split[0].equals("todo")) {
                addToTasks(new ToDo(userInput.substring(5)));
                continue;
            }

            if (split[0].equals("deadline")) {
                int index = userInput.indexOf("/by");
                addToTasks(new Deadline(userInput.substring(9, index), userInput.substring(index + 4)));
                continue;
            }

            if (split[0].equals("event")) {
                int index = userInput.indexOf("/at");
                addToTasks(new Event(userInput.substring(6, index), userInput.substring(index + 4)));
                continue;
            }
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

    private static void addToTasks(Task task) {
        TASKS.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("%s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", TASKS.size()));
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.println(String.format("%d. %s", 1 + i, TASKS.get(i).toString()));
        }
    }
}
