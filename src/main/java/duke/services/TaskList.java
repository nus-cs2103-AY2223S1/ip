package duke.services;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/** Handles tasks */
public class TaskList {
    /** The tasks stored */
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Gets the tasks stored
     */
    public static List<Task> getTasks() {
        return tasks;
    }

    /**
     * Stores the task and displays outcome
     */
    public static void addTask(Task task) {
        tasks.add(task);
        UI.sayLines(new String[] {
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
    public static void addTodo(String[] words) {
        addTask(new Todo(Parser.getDescription(words, null)));
    }

    /**
     * Stores a Deadline outlined in the command and displays outcome
     * @param words The words of the command entered, first is always "deadline"
     * @throws IllegalArgumentException if words specifies an empty description or empty date or is missing /by
     */
    public static void addDeadline(String[] words) {
        addTask(new Deadline(Parser.getDescription(words, "/by"), Parser.getTiming(words, "/by")));
    }

    /**
     * Stores an Event outlined in the command and displays outcome
     * @param words The words of the command entered, first is always "event"
     * @throws IllegalArgumentException If words specifies an empty description or empty date or is missing /at
     */
    public static void addEvent(String[] words) {
        addTask(new Event(Parser.getDescription(words, "/at"), Parser.getTiming(words, "/at")));
    }

    /**
     * Marks the specified task as done and displays outcome
     * @param words The words of the command entered, first is always "mark"
     * @throws IllegalArgumentException If words has an invalid number of arguments or invalid argument value
     */
    public static void markTaskAsDone(String[] words) {
        Task task = tasks.get(Parser.getTaskNumber(words) - 1).markAsDone();
        UI.sayLines(new String[]{
            "Nice! I've marked this task as done:",
            "  " + task
        });
    }

    /**
     * Marks the specified task as not done and displays outcome
     * @param words The words of the command entered, first is always "unmark"
     * @throws IllegalArgumentException If words has an invalid number of arguments or invalid argument value
     */
    public static void markTaskAsNotDone(String[] words) {
        Task task = tasks.get(Parser.getTaskNumber(words) - 1).markAsNotDone();
        UI.sayLines(new String[]{
            "OK, I've marked this task as not done yet:",
            "  " + task
        });
    }

    /**
     * Deletes the specified task and displays outcome
     * @param words The words of the command entered, first is always "delete"
     * @throws IllegalArgumentException If words has an invalid number of arguments or invalid argument value
     */
    public static void deleteTask(String[] words) {
        Task removedTask = tasks.remove(Parser.getTaskNumber(words) - 1);
        UI.sayLines(new String[]{
            "Noted. I've removed this task:",
            "  " + removedTask,
            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
        });
    }

    /**
     * Lists out information on all tasks stored
     */
    public static void listTasks() {
        String[] taskDescriptions = new String[getTasks().size() + 1];
        taskDescriptions[0] = "Here are the tasks in your list:";
        for (int i = 1; i < taskDescriptions.length; ++i) {
            taskDescriptions[i] = "  " + i + "." + tasks.get(i - 1);
        }
        UI.sayLines(taskDescriptions);
    }

    /**
     * Displays all tasks whose descriptions contain the keyword specified in words
     * @param words The words of the command entered, first is always "find"
     */
    public static void findTasksContainingKeyword(String[] words) {
        StringBuilder keywordBuilder = new StringBuilder();
        for (int i = 1; i < words.length; ++i) {
            keywordBuilder.append(words[i]).append(" ");
        }
        String keyword = words.length == 1 ? "" : keywordBuilder.deleteCharAt(keywordBuilder.length() - 1).toString();

        ArrayList<String> matchingTasks = new ArrayList<String>();
        matchingTasks.add("Here are the tasks containing the keyword \"" + keyword + "\" :");
        for (int i = 0; i < tasks.size(); ++i) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().contains(keyword)) {
                matchingTasks.add("  " + (i + 1) + "." + currTask);
            }
        }
        UI.sayLines(matchingTasks.toArray(String[]::new));
    }
}
