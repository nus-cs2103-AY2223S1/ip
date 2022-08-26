package duke.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import duke.tasks.*;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    /** The tasks stored */
    private static final List<Task> tasks = new ArrayList<>();

    /** Points to the current word being read in the current command */
    private static int currWordIndex = 0;

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
     * Displays the lines using a format
     */
    private static void sayLines(String[] lines) {
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Stores the task and displays outcome
     */
    private static void addTask(Task task) {
        tasks.add(task);
        sayLines(new String[] {
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
        });
    }

    /**
     * Stores a Todo outlined in the command and displays outcome
     * @param words The words of the command entered, first is always "todo"
     * @throws IllegalArgumentException If words specifies an empty description
     */
    private static void addTodo(String[] words) {
        addTask(new Todo(getDescription(words, null)));
    }

    /**
     * Stores a Deadline outlined in the command and displays outcome
     * @param words The words of the command entered, first is always "deadline"
     * @throws IllegalArgumentException if words specifies an empty description or empty date or is missing /by
     */
    private static void addDeadline(String[] words) {
        addTask(new Deadline(getDescription(words, "/by"), getTiming(words, "/by")));
    }

    /**
     * Stores an Event outlined in the command and displays outcome
     * @param words The words of the command entered, first is always "event"
     * @throws IllegalArgumentException If words specifies an empty description or empty date or is missing /at
     */
    private static void addEvent(String[] words) {
        addTask(new Event(getDescription(words, "/at"), getTiming(words, "/at")));
    }

    /**
     * Retrieves the description argument in the command
     * @param words The words of the command entered, first is some valid command name
     * @param stop The word before which the description ends
     * @return The description specified in words
     * @throws IllegalArgumentException If words specifies an empty description
     */
    private static String getDescription(String[] words, String stop) {
        StringBuilder descBuilder = new StringBuilder();
        boolean emptyDesc = true;

        while (currWordIndex < words.length && !words[currWordIndex].equals(stop)) {
            if (words[currWordIndex].isEmpty()) {
                descBuilder.append(" ");
            } else {
                descBuilder.append(words[currWordIndex]).append(" ");
                emptyDesc = false;
            }
            ++currWordIndex;
        }

        if (emptyDesc) {
            throw new IllegalArgumentException("☹ OOPS!!! Description can't be empty");
        }

        return descBuilder.deleteCharAt(descBuilder.length()-1).toString();
    }

    /**
     * Retrieves the timing argument in the command
     * @param words The words of the command entered, first is some valid command name
     * @param flag The flag that the timing belongs to
     * @return The timing specified in words
     * @throws IllegalArgumentException If words specifies an empty timing or is missing flag
     */
    private static String getTiming(String[] words, String flag) {
        if (currWordIndex >= words.length) {
            throw new IllegalArgumentException("☹ OOPS!!! " + flag + " not found");
        } else if (currWordIndex == words.length - 1) {
            throw new IllegalArgumentException("☹ OOPS!!! Timing for " + flag + " can't be empty");
        }

        ++currWordIndex;
        StringBuilder timingBuilder = new StringBuilder();

        while (currWordIndex < words.length) {
            timingBuilder.append(words[currWordIndex++]).append(" ");
        }

        return timingBuilder.deleteCharAt(timingBuilder.length()-1).toString();
    }

    /**
     * Marks the specified task as done and displays outcome
     * @param words The words of the command entered, first is always "mark"
     * @throws IllegalArgumentException If the argument(s) supplied in words isn't an integer from 1 to the number of stored tasks
     */
    private static void markTaskAsDone(String[] words) {
        Task task = tasks.get(getTaskNumber(words) - 1).markAsDone();
        sayLines(new String[]{
                "Nice! I've marked this task as done:",
                "  " + task
        });
    }

    /**
     * Marks the specified task as not done and displays outcome
     * @param words The words of the command entered, first is always "unmark"
     * @throws IllegalArgumentException If the argument(s) supplied in words isn't an integer from 1 to the number of stored tasks
     */
    private static void markTaskAsNotDone(String[] words) {
        Task task = tasks.get(getTaskNumber(words) - 1).markAsNotDone();
        sayLines(new String[]{
                "OK, I've marked this task as not done yet:",
                "  " + task
        });
    }

    /**
     * Deletes the specified task and displays outcome
     * @param words The words of the command entered, first is always "delete"
     * @throws IllegalArgumentException If the argument(s) supplied in words isn't an integer from 1 to the number of stored tasks
     */
    private static void deleteTask(String[] words) {
        Task removedTask = tasks.remove(getTaskNumber(words) - 1);
        sayLines(new String[]{
                "Noted. I've removed this task:",
                "  " + removedTask,
                "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
        });
    }

    /**
     * Gets the task number (integer pointing to a task) specified in the command
     *
     * @param words The words of the command entered, first is always some valid command name
     * @return The task number specified
     * @throws IllegalArgumentException If the argument(s) supplied in words isn't an integer from 1 to the number of stored tasks
     */
    private static int getTaskNumber(String[] words) {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("☹ OOPS!!! No tasks stored for me to do that");
        }

        int taskNumber = 0;

        try {
            taskNumber = (words.length == 2) ? Integer.parseInt(words[currWordIndex]) : 0;
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("☹ OOPS!!! The task number must be from 1 to " + tasks.size());
        }

        return taskNumber;
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
                currWordIndex = 1;
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
                    } else if (words[0].equals("delete")) {
                        deleteTask(words);
                    } else {
                        sayLines(new String[]{"I'm sorry, I don't know what that means"});
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