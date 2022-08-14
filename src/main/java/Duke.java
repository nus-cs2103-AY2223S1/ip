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
            try {
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
                    markTaskAsDone(getTask(Integer.parseInt(split[1]) - 1));
                    continue;
                }

                if (split.length == 2 && isNumeric(split[1]) && split[0].equals("unmark")) {
                    markTaskAsUndone(getTask(Integer.parseInt(split[1]) - 1));
                    continue;
                }

                if (split.length == 2 && isNumeric(split[1]) && split[0].equals("delete")) {
                    deleteTask(getTask(Integer.parseInt(split[1]) - 1));
                    continue;
                }

                if (split[0].equals("todo")) {
                    addToDo(userInput.substring(4).trim());
                    continue;
                }

                if (split[0].equals("deadline")) {
                    int index = userInput.indexOf("/by");
                    addDeadline(userInput.substring(8, index).trim(), userInput.substring(index + 3).trim());
                    continue;
                }

                if (split[0].equals("event")) {
                    int index = userInput.indexOf("/at");
                    addEvent(userInput.substring(5, index).trim(), userInput.substring(index + 3).trim());
                    continue;
                }

                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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

    private static Task getTask(int index) throws DukeException {
        if (index < 0 || index >= TASKS.size()) {
            throw new DukeException("OOPS!!! The task number is out of bounds.");
        }
        return TASKS.get(index);
    }

    private static void deleteTask(Task task) {
        TASKS.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", TASKS.size()));
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

    private static void addToDo(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        addToTasks(new ToDo(description));
    }

    private static void addDeadline(String description, String by) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by.length() == 0) {
            throw new DukeException("OOPS!!! The date of a deadline cannot be empty.");
        }
        addToTasks(new Deadline(description, by));
    }

    private static void addEvent(String description, String at) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        if (at.length() == 0) {
            throw new DukeException("OOPS!!! The date of an event cannot be empty.");
        }
        addToTasks(new Event(description, at));
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.println(String.format("%d. %s", 1 + i, TASKS.get(i).toString()));
        }
    }
}
