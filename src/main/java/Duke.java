import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static final int HORIZONTAL_LINE_LENGTH = 50;
    private static final int TASK_LIST_LIMIT = 100;
    private static final String NAME = "Duke";
    private static final String[] EVENT_NAMES = {"todo", "deadline", "event"};

    public static void printIndented(Object obj) {
        System.out.println("    " + obj.toString().replace("\n", "\n    "));
    }

    public static void printHorizontalLine() {
        printIndented("_".repeat(HORIZONTAL_LINE_LENGTH));
    }

    public static void printTasks(Task[] tasks) {
        printIndented("Here are the tasks in your list:");
        for (int i = 1; i > tasks.length || tasks[i - 1] != null; i++) {
            printIndented(i + "." + tasks[i - 1]);
        }
    }

    public static void sayGoodbye() {
        printIndented("Bye. Hope to see you again soon!");
    }

    public static void markTaskAsDone(Task[] tasks, int taskID) {
        printIndented("Nice! I've marked this task as done:");
        tasks[taskID - 1].markAsDone();
        printIndented(tasks[taskID - 1]);
    }

    public static void unmarkTaskAsNotDone(Task[] tasks, int taskID) {
        printIndented("OK, I've marked this task as not done yet:");
        tasks[taskID - 1].unmarkAsNotDone();
        printIndented(tasks[taskID - 1]);
    }

    public static int createNewTask(Task[] tasks, String input, int pointer) {
        String taskName;
        String by;
        String at;
        try {
            if (input.startsWith("todo")) {
                taskName = input.replace("todo", "").strip();
                if (taskName.isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                tasks[pointer++] = new ToDos(taskName);
            } else if (input.startsWith("deadline")) {
                String[] temp = input.replace("deadline", "").strip().split("/by");
                taskName = temp[0].strip();
                if (taskName.isEmpty()) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                by = temp[1].strip();
                tasks[pointer++] = new Deadlines(taskName, by);
            } else if (input.startsWith("event")) {
                String[] temp = input.replace("event", "").strip().split("/at");
                taskName = temp[0].strip();
                if (taskName.isEmpty()) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                at = temp[1].strip();
                tasks[pointer++] = new Events(taskName, at);
            }
            printIndented("Got it. I've added this task:\n  " + tasks[pointer - 1]
                    + "\nNow you have " + pointer + " tasks in the list.");
        } catch (DukeException d) {
            printIndented(d.getMessage());
        }
        return pointer;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[TASK_LIST_LIMIT];
        int pointer = 0;
        printHorizontalLine();
        printIndented("Hello! I'm " + NAME + "\nWhat can I do for you?");
        printHorizontalLine();
        while (true) {
            String input = sc.nextLine();
            String keyword = input.split(" ")[0];
            printHorizontalLine();
            switch (keyword) {
                case "bye":
                    sayGoodbye();
                    printHorizontalLine();
                    return;
                case "list":
                    printTasks(tasks);
                    break;
                case "mark":
                    markTaskAsDone(tasks, Integer.parseInt(input.split(" ")[1]));
                    break;
                case "unmark":
                    unmarkTaskAsNotDone(tasks, Integer.parseInt(input.split(" ")[1]));
                    break;
                default:
                    try {
                        if (Arrays.asList(EVENT_NAMES).contains(keyword)) {
                            pointer = createNewTask(tasks, input, pointer);
                        } else {
                            throw new DukeException("Sorry, I don't understand :(");
                        }
                    } catch (DukeException d) {
                        printIndented(d.getMessage());
                    }
                    break;
            }
            printHorizontalLine();
        }
    }
}
