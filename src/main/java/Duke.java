import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Personal Assistant that helps you keep track of your tasks/
 * Task descriptions given are autocorrected to have only 1 white space
 */
public class Duke {

    /** The tasks stored */
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Prints Duke's greeting on opening the app
     */
    private static void introduceSelf() {
        sayLines(new String[] {
                "Hello! I'm Duke",
                "What can I do for you?",
        });
    }

    /**
     * Displays the given lines using a format
     */
    private static void sayLines(String[] lines) {
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Stores the given task and notifies user of outcome
     */
    private static void addTask(Task task) {
        tasks.add(task);
        sayLines(new String[] {
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list."
        });
    }

    /**
     * Tries to store a Todo with the given specifications and notifies user of outcome
     * @param words The words of the command entered, first is always "todo"
     */
    private static void addTodo(String[] words) throws IllegalArgumentException {
        if (words.length == 1) {
            throw new IllegalArgumentException("☹ OOPS!!! Description can't be empty");
        }
        StringBuilder descBuilder = new StringBuilder();
        int currIndex = 1;
        while (currIndex < words.length) {
            descBuilder.append(words[currIndex++]).append(" ");
        }
        descBuilder.deleteCharAt(descBuilder.length()-1);
        addTask(new Todo(descBuilder.toString()));
    }

    /**
     * Tries to store a Deadline with the given specifications and notifies user of outcome
     * @param words The words of the command entered, first is always "deadline"
     */
    private static void addDeadline(String[] words) throws IllegalArgumentException {
        StringBuilder descBuilder = new StringBuilder();
        int currIndex = 1;
        boolean emptyDesc = true;
        while (currIndex < words.length && !words[currIndex].equals("/by")) {
            if (words[currIndex].isEmpty()) {
                descBuilder.append(" ");
            } else {
                descBuilder.append(words[currIndex]).append(" ");
                emptyDesc = false;
            }
            ++currIndex;
        }
        if (currIndex == words.length) {
            throw new IllegalArgumentException("☹ OOPS!!! /by flag not found");
        } else if (emptyDesc) {
            throw new IllegalArgumentException("☹ OOPS!!! Description can't be empty");
        } else if (currIndex == words.length - 1) {
            throw new IllegalArgumentException("☹ OOPS!!! Date/time for /by flag can't be empty");
        }
        descBuilder.deleteCharAt(descBuilder.length()-1);

        ++currIndex;
        StringBuilder byBuilder = new StringBuilder();
        while (currIndex < words.length) {
            byBuilder.append(words[currIndex++]).append(" ");
        }
        byBuilder.deleteCharAt(byBuilder.length()-1);

        addTask(new Deadline(descBuilder.toString(), byBuilder.toString()));
    }

    /**
     * Tries to store an Event with the given specifications and notifies user of outcome
     * @param words The words of the command entered, first is always "event"
     */
    private static void addEvent(String[] words) throws IllegalArgumentException {
        StringBuilder descBuilder = new StringBuilder();
        int currIndex = 1;
        boolean emptyDesc = true;
        while (currIndex < words.length && !words[currIndex].equals("/at")) {
            if (words[currIndex].isEmpty()) {
                descBuilder.append(" ");
            } else {
                descBuilder.append(words[currIndex]).append(" ");
                emptyDesc = false;
            }
            ++currIndex;
        }
        if (currIndex == words.length) {
            throw new IllegalArgumentException("☹ OOPS!!! /at flag not found");
        } else if (emptyDesc) {
            throw new IllegalArgumentException("☹ OOPS!!! Description can't be empty");
        } else if (currIndex == words.length - 1) {
            throw new IllegalArgumentException("☹ OOPS!!! Date/time for /at flag can't be empty");
        }
        descBuilder.deleteCharAt(descBuilder.length()-1);

        ++currIndex;
        StringBuilder atBuilder = new StringBuilder();
        while (currIndex < words.length) {
            atBuilder.append(words[currIndex++]).append(" ");
        }
        atBuilder.deleteCharAt(atBuilder.length()-1);

        addTask(new Event(descBuilder.toString(), atBuilder.toString()));
    }

    /**
     * Tries to mark the specified task as done and notifies user of outcome
     * @param words The words of the command entered, first is always "mark"
     */
    private static void markTaskAsDone(String[] words) throws IllegalArgumentException {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("☹ OOPS!!! No tasks stored for me to do that");
        }

        Task task;
        try {
            int taskNumber = (words.length == 1) ? 0 : Integer.parseInt(words[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IllegalArgumentException();
            }
            task = tasks.get(taskNumber - 1);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("☹ OOPS!!! The task number must be from 1 to " + tasks.size());
        }

        task.markAsDone();
        sayLines(new String[]{
                "Nice! I've marked this task as done:",
                "  " + task
        });
    }

    /**
     * Tries to mark the specified task as not done and notifies user of outcome
     * @param words The words of the command entered, first is always "unmark"
     */
    private static void markTaskAsNotDone(String[] words) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("☹ OOPS!!! No tasks stored for me to do that");
        }

        Task task;
        try {
            int taskNumber = (words.length == 1) ? 0 : Integer.parseInt(words[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IllegalArgumentException();
            }
            task = tasks.get(taskNumber - 1);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("☹ OOPS!!! The task number must be from 1 to " + tasks.size());
        }

        task.markAsNotDone();
        sayLines(new String[]{
                "OK, I've marked this task as not done yet:",
                "  " + task
        });
    }

    /**
     * Lists out information on all tasks stored
     */
    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints Duke's closing statement on exiting the app
     */
    private static void sayGoodbye() {
        sayLines(new String[] {
                "Bye. Hope to see you again soon!",
        });
    }

    public static void main(String[] args) {
        introduceSelf();

        Scanner inputScanner = new Scanner(System.in);
        String[] words = Arrays.stream(inputScanner.nextLine().strip().split(" ")).toArray(String[]::new);
        while (!(words.length == 1 && words[0].equals("bye"))) {
            if (words.length > 0) {
                try {
                    if (words.length == 1 && words[0].equals("list")) {
                        listTasks(); //could put words.length == 1 cases all here
                    } else if (words[0].equals("todo")) {
                        addTodo(words);
                    } else if (words[0].equals("deadline")) {
                        addDeadline(words);
                    } else if (words[0].equals("event")) {
                        addEvent(words);
                    } else if (words[0].equals("mark")) {
                        markTaskAsDone(words);
                    } else if (words[0].equals("unmark")) {
                        markTaskAsNotDone(words);
                    } else {
                        sayLines(new String[]{"I'm sorry but I don't know what that means"});
                    }
                } catch (IllegalArgumentException e) {
                    sayLines(new String[]{e.getMessage()});
                }
            }
            words = Arrays.stream(inputScanner.nextLine().strip().split(" ")).toArray(String[]::new);
        }
        inputScanner.close();

        sayGoodbye();
    }
}
