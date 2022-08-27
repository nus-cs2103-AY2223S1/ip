package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * Class that handles input and output between the user.
 */
public class Ui {
    private static final String PADDING = "  ";

    /**
     * Returns formatted welcome message.
     *
     * @return Welcome message.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Duke.\n"
                + "What can I do for you?";
    }

    /**
     * Pads the text with 2 spaces.
     *
     * @param text text to pad.
     * @return Left padded text.
     */
    public static String leftPad(String text) {
        return PADDING + text;
    }

    /**
     * Joins texts with new line and returns the result as a String.
     *
     * @param texts Variable number of texts to display to ther user.
     */
    public static String joinTextsWithNewLine(String... texts) {
        return String.join("\n", texts);
    }

    /**
     * Joins texts with new line and returns the result as a String.
     *
     * @param texts List of number of texts to display to ther user.
     */
    public static String joinTextsWithNewLine(List<String> texts) {
        return String.join("\n", texts);
    }

    /**
     * Formats and returns error message passed in.
     *
     * @param errorMessage Message to display.
     * @return Formatted error message.
     */
    public static String getErrorMessage(String errorMessage) {
        return "Error! " + errorMessage;
    }

    /**
     * Returns a list of tasks that is formatted by {@code formatTaskList}.
     *
     * @param tasks List of tasks.
     * @return A single String consisting of an indexed list of tasks.
     */
    public static String getTaskListMessage(List<Task> tasks) {
        List<String> toPrint = formatTaskList(tasks);
        return joinTextsWithNewLine(toPrint);
    }

    /**
     * Returns a String consisting a list of tasks that is formatted by
     * {@code formatTaskList} along with a search message. Will return an
     * unsuccessful message if task list is empty.
     *
     * @param tasks List of tasks.
     * @return A single String consisting of an indexed list of matched tasks.
     */
    public static String getTaskListSearchMessage(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No task matched your query!";
        }
        return "Here is what I found:" + "\n"
                + formatTaskList(tasks);
    }

    /**
     * Returns an add task message.
     *
     * @param task Task that was added.
     * @param taskListSize Size of {@code TaskList}.
     * @return Add task message.
     */
    public static String getAddTaskMessage(Task task, int taskListSize) {
        return getUpdateMessage(task, "Task added: ", taskListSize);
    }

    /**
     * Returns a delete task message.
     *
     * @param task task that was deleted.
     * @param taskListSize size of TaskList.
     * @return Delete task message.
     */
    public static String getDeleteTaskMessage(Task task, int taskListSize) {
        return getUpdateMessage(task, "Task deleted: ", taskListSize);
    }

    /**
     * Returns a mark task message.
     *
     * @param task Task that was marked.
     * @return Mark task message;
     */
    public static String getMarkTaskMessage(Task task) {
        return getUpdateMessage(task, "I have marked this task as done: ");
    }

    /**
     * Returns an unmark task message.
     *
     * @param task Task that was unmarked.
     * @return Unmark task message;
     */
    public static String displayUnmarkTaskMessage(Task task) {
        return getUpdateMessage(task, "I have unmarked the completion of this task: ");
    }

    /**
     * Returns an exit message.
     *
     * @return Exit message;
     */
    public static String getExitMessage() {
        return "Bye bye!";
    }

    /**
     * Returns an unknown command message.
     *
     * @param input unknown command text.
     * @return Unknown command message;
     */
    public static String displayUnknownCommandMessage(String input) {
        return "Unknown command: " + input;
    };

    /**
     * Returns a String containing the {@code updateMessage} followed by the
     * {@code task} in a separate line.
     *
     * @param task {@code Task} object to display in the message.
     * @param updateMessage Message to display before the {@code Task} object.
     * @return String containing {@updateMessage} and {@code Task}.
     */
    private static String getUpdateMessage(Task task, String updateMessage) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add(updateMessage);
        toPrint.add(leftPad(task.toString()));
        return joinTextsWithNewLine(toPrint);
    }

    /**
     * Works just like {@link Ui#getUpdateMessage(Task, String)} except that there
     * is another line for reporting of {@code taskListSize}.
     *
     * @see Ui#getUpdateMessage(Task, String)
     */
    private static String getUpdateMessage(Task task, String updateMessage, int taskListSize) {
        return getUpdateMessage(task, updateMessage) + "\n"
                + "There are now " + taskListSize + " tasks in the list.";
    }

    /**
     * Returns a list of strings corresponding to the task in the list.
     * Each string is prefixed with their corresponding index in the list.
     *
     * @param tasks List of tasks.
     * @return List of task Strings.
     */
    private static List<String> formatTaskList(List<Task> tasks) {
        List<String> indexedList = IntStream.range(0,
                tasks.size()).mapToObj((index) -> String.format("%d. %s", index + 1, tasks.get(index).toString()))
                .collect(Collectors.toList());
        return indexedList;
    }

}
