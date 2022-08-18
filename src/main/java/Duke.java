import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    private static void markTask(String input, boolean done) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task");
        }
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.get(taskIdx - 1);
            if (done) {
                task.mark();
            } else {
                task.unmark();
            }
            String successMessage = done
                    ? "Nice! I've marked this task as done:\n  %s\n"
                    : "OK, I've marked this task as not done yet:\n  %s\n";
            System.out.printf(successMessage, task.toString());
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    private static void deleteTask(String input) throws DukeException {
        String[] splitInput = input.split("delete ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task to delete");
        }
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.remove(taskIdx - 1);
            System.out.printf("Noted. I've removed this task:\n  %s\n", task.toString());
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    private static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("You currently have no tasks. ");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
    }

    enum TaskType {
        TODO("todo "),
        DEADLINE("deadline "),
        EVENT("event ");

        public final String string;

        TaskType(String string) {
            this.string = string;
        }
    }

    private static void addTask(String input, TaskType type) throws DukeException {
        String[] splitInput = input.split(type.string);
        String errorMessage = type == TaskType.TODO
                ? "Please add a description for the %s"
                : "Please add a description and date for the %s";
        if (splitInput.length < 2) {
            throw new DukeException(String.format(errorMessage, type.string));
        }
        Task task;
        if (type == TaskType.TODO) {
            String desc = splitInput[1];
            task = new Todo(desc);
        } else {
            splitInput = splitInput[1].split(type == TaskType.DEADLINE ? " /by " : " /at ");
            if (splitInput.length < 2) {
                throw new DukeException(String.format(errorMessage, type.string));
            }
            task = type == TaskType.DEADLINE
                    ? new Deadline(splitInput[0], splitInput[1])
                    : new Event(splitInput[0], splitInput[1]);
        }
        tasks.add(task);
        System.out.printf("Got it. I've added this task:\n  %s\n", task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                           "What can I do for you?\n");
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    printTasks();
                } else if (input.startsWith("mark ")) {
                    markTask(input, true);
                } else if (input.startsWith("unmark ")) {
                    markTask(input, false);
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else if (input.startsWith("todo ")) {
                    addTask(input, TaskType.TODO);
                } else if (input.startsWith("deadline ")) {
                    addTask(input, TaskType.DEADLINE);
                } else if (input.startsWith("event ")) {
                    addTask(input, TaskType.EVENT);
                } else {
                    throw new DukeException("what");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
