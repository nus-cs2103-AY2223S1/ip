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
        Task task;
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            task = tasks.get(taskIdx - 1);
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

    private static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("No tasks to show");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
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
                } else if (input.startsWith("todo ")) {
                    String[] splitInput = input.split("todo ");
                    if (splitInput.length < 2) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String desc = splitInput[1];
                    Todo todo = new Todo(desc);
                    tasks.add(todo);
                    System.out.printf("Got it. I've added this task:\n  %s\n", todo.toString());
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                } else if (input.startsWith("deadline ")) {
                    String[] splitInput = input.split("deadline ");
                    if (splitInput.length < 2) {
                        throw new DukeException("Please add a description and date for the deadline");
                    }
                    splitInput = splitInput[1].split(" /by ");
                    if (splitInput.length < 2) {
                        throw new DukeException("Please add a description and date for the deadline");
                    }
                    Deadline deadline = new Deadline(splitInput[0], splitInput[1]);
                    tasks.add(deadline);
                    System.out.printf("Got it. I've added this task:\n  %s\n", deadline.toString());
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                } else if (input.startsWith("event ")) {
                    String[] splitInput = input.split("event ");
                    if (splitInput.length < 2) {
                        throw new DukeException("Please add a description and date for the event");
                    }
                    splitInput = splitInput[1].split(" /at ");
                    if (splitInput.length < 2) {
                        throw new DukeException("Please add a description and date for the event");
                    }
                    Event event = new Event(splitInput[0], splitInput[1]);
                    tasks.add(event);
                    System.out.printf("Got it. I've added this task:\n  %s\n", event.toString());
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                } else {
                    throw new DukeException("what");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
