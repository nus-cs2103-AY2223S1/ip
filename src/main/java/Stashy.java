import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

/**
 * Represents a chatbot that can do many things, just like
 * what a regular chatbot should be able to do.
 */
public class Stashy {
    private static final int HORIZONTAL_LINE_LENGTH = 50;
    private static final String NAME = "Stashy";
    private static final String DATA_FILEPATH = "src/main/data/duke.txt";

    /**
     * Prints multiple lines, each indented by 4 spaces.
     *
     * @param obj Any printable object
     */
    public static void printIndented(Object obj) {
        System.out.println("    " + obj.toString().replace("\n", "\n    "));
    }

    /**
     * Prints a horizontal line, good to separate the input command and the output.
     */
    public static void printHorizontalLine() {
        printIndented("_".repeat(HORIZONTAL_LINE_LENGTH));
    }

    /**
     * Prints the task list in a tidy manner.
     *
     * @param tasks The list of tasks
     */
    public static void printTasks(List<Task> tasks) {
        printIndented("Listing all task(s) in your list...");
        for (int i = 1; i <= tasks.size(); i++) {
            printIndented(i + "." + tasks.get(i - 1));
        }
    }

    /**
     * Prints the end message.
     */
    public static void sayGoodbye() {
        printIndented(NAME + " says goodbye!");
    }

    /**
     * Marks a particular task from the given task list as done.
     *
     * @param tasks     The list of tasks
     * @param input     Raw input string to be processed within
     * @throws StashyException If any invalid input is given
     */
    public static void markTaskAsDone(List<Task> tasks, String input) throws StashyException {
        int taskID;
        try {
            String keyword = input.split(" ")[0];
            taskID = Integer.parseInt(input.replace(keyword, "").strip());
        } catch (NumberFormatException e) {
            throw new StashyException("Task ID is not an integer, how come!");
        }
        if (1 <= taskID && taskID <= tasks.size()) {
            printIndented("LFG, marking this task as done!");
            tasks.get(taskID - 1).markAsDone();
            printIndented("  " + tasks.get(taskID - 1));
        } else {
            throw new StashyException("Invalid task ID: " + taskID);
        }
    }

    /**
     * Unmarks a particular task from the given task list as not done.
     *
     * @param tasks The list of tasks
     * @param input     Raw input string to be processed within
     * @throws StashyException If any invalid input is given
     */
    public static void unmarkTaskAsNotDone(List<Task> tasks, String input) throws StashyException {
        int taskID;
        try {
            String keyword = input.split(" ")[0];
            taskID = Integer.parseInt(input.replace(keyword, "").strip());
        } catch (NumberFormatException e) {
            throw new StashyException("Task ID is not an integer, how come!");
        }
        if (1 <= taskID && taskID <= tasks.size()) {
            printIndented("L + ratio, unmarking this task as not done!");
            tasks.get(taskID - 1).unmarkAsNotDone();
            printIndented("  " + tasks.get(taskID - 1));
        } else {
            throw new StashyException("Invalid task ID: " + taskID);
        }
    }

    /**
     * Deletes a particular task from the given task list.
     *
     * @param tasks     The list of tasks
     * @param input     Raw input string to be processed within
     * @throws StashyException If any invalid input is given
     */
    public static void deleteTask(List<Task> tasks, String input) throws StashyException {
        int taskID;
        try {
            String keyword = input.split(" ")[0];
            taskID = Integer.parseInt(input.replace(keyword, "").strip());
        } catch (NumberFormatException e) {
            throw new StashyException("Task ID is not an integer, how come!");
        }
        if (1 <= taskID && taskID <= tasks.size()) {
            printIndented("Task has been removed!\n  " + tasks.get(taskID - 1));
            tasks.remove(taskID - 1);
            printIndented("You have " + tasks.size() + " task(s) in the list.");
        } else {
            throw new StashyException("Invalid task ID: " + taskID);
        }
    }

    /**
     * Creates a Task object based on the input string.
     *
     * @param tasks The list of tasks
     * @param input The input string, consists of the keyword, the task name, and the by/at metadata
     * @throws StashyException If any invalid input is given
     */
    public static void createNewTask(List<Task> tasks, String input) throws StashyException {
        String taskName;
        String by;
        String at;
        if (input.startsWith("todo")) {
            taskName = input.replace("todo", "").strip();
            if (taskName.isEmpty()) {
                throw new StashyException("Please don't give me an empty todo description :(");
            }
            tasks.add(new ToDo(taskName));
        } else if (input.startsWith("deadline")) {
            String[] temp = input.replace("deadline", "").strip().split("/by");
            taskName = temp[0].strip();
            if (taskName.isEmpty()) {
                throw new StashyException("Please don't give me an empty deadline description :(");
            } else if (temp.length < 2) {
                throw new StashyException("Specify the due date of the deadline, perhaps?");
            }
            by = temp[1].strip();
            if (by.isEmpty()) {
                throw new StashyException("Specify the due date of the deadline, perhaps?");
            }
            tasks.add(new Deadline(taskName, by));
        } else if (input.startsWith("event")) {
            String[] temp = input.replace("event", "").strip().split("/at");
            taskName = temp[0].strip();
            if (taskName.isEmpty()) {
                throw new StashyException("Please don't give me an empty event description :(");
            } else if (temp.length < 2) {
                throw new StashyException("Specify the time of the event, perhaps?");
            }
            at = temp[1].strip();
            if (at.isEmpty()) {
                throw new StashyException("Specify the time of the event, perhaps?");
            }
            tasks.add(new Event(taskName, at));
        }
        printIndented("There, we have a new task:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * The main function of this chatbot.
     */
    public static void main(String[] args) {
        TaskReader tr = new TaskReader();
        Scanner sc = new Scanner(System.in);
        try {
            List<Task> tasks = tr.convertToTaskList(DATA_FILEPATH);
            printHorizontalLine();
            printIndented("Beep boop! I'm " + NAME + "\nWhat can I do for you, busy guy?");
            printHorizontalLine();
            while (true) {
                String input = sc.nextLine().strip();
                String keyword = input.split(" ")[0];
                printHorizontalLine();
                try {
                    switch (keyword) {
                        case "bye":
                            sayGoodbye();
                            printHorizontalLine();
                            try {
                                tr.writeTaskListToFile(tasks, DATA_FILEPATH);
                            } catch (IOException ioe) {
                                printIndented(ioe.getMessage() + "\nData not saved :((");
                            }
                            return;
                        case "list":
                            printTasks(tasks);
                            break;
                        case "mark":
                            markTaskAsDone(tasks, input);
                            break;
                        case "unmark":
                            unmarkTaskAsNotDone(tasks, input);
                            break;
                        case "delete":
                            deleteTask(tasks, input);
                            break;
                        case "todo":
                        case "deadline":
                        case "event":
                            createNewTask(tasks, input);
                            break;
                        default:
                            throw new StashyException("I have no idea what are you saying :<");
                    }
                } catch (StashyException s) {
                    printIndented(s.getMessage());
                }
                printHorizontalLine();
            }
        } catch (FileNotFoundException e) {
            printHorizontalLine();
            printIndented(e.getMessage() + "\nCreating file and/or directory...");
            try {
                tr.createDataFile(DATA_FILEPATH);
            } catch (IOException ioe) {
                printIndented(ioe.getMessage());
            }
            printHorizontalLine();
        }
    }
}
