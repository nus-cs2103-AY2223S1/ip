package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * Class that handles input and output between the user.
 */
public class Ui {
    public static final String DIVIDER = "    ____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String PADDING = "  ";
    private Scanner scanner;

    /**
     * Constructor for Ui. Initialises input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return User input.
     * @throws DukeException When error reading input.
     */
    String readCommand() throws DukeException {
        try {
            return this.scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeException("Can't read command!", e);
        }
    }

    /**
     * Pads the text.
     *
     * @param text Text to pad.
     * @return Left padded text.
     */
    String leftPad(String text) {
        return PADDING + text;
    }

    /**
     * Formats texts before displaying them to the user.
     *
     * @param texts Texts to display to the user.
     */
    public void formatAndPrint(List<? extends String> texts) {
        System.out.println(DIVIDER);
        texts.forEach((text) -> System.out.println(leftPad(leftPad(" " + text))));
        System.out.println(DIVIDER);
    }

    /**
     * Formats text before displaying it to the user.
     *
     * @param text Text to display to ther user.
     */
    void formatAndPrint(String text) {
        formatAndPrint(List.of(text));
    }

    /**
     * Displays error message to the user.
     *
     * @param errorMessage Message to display.
     */
    void displayErrorMessage(String errorMessage) {
        formatAndPrint("Error! " + errorMessage);
    }

    /**
     * Displays welcome message to the user.
     */
    void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        String[] messages = { "Hello! I'm Duke.", "What can I do for you?" };
        formatAndPrint(List.<String>of(messages));
    }

    /**
     * Wrapper funtion for displaying update message.
     *
     * @param task Task that was added / deleted / updated.
     * @param updateMessage Message to display first.
     * @param taskListSize Size of TaskList.
     */
    private void displayUpdateMessage(Task task, String updateMessage, Optional<Integer> taskListSize) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add(updateMessage);
        toPrint.add(leftPad(task.toString()));
        taskListSize.ifPresent((size) -> toPrint.add("There are now " + size + " tasks in the list."));
        formatAndPrint(toPrint);
    }

    /**
     * Returns a list of strings corresponding to the task in the list.
     * Each string is prefixed with their corresponding index in the list.
     *
     * @param tasks List of tasks.
     *
     * @return List of task Strings.
     */
    List<String> formatTaskList(List<Task> tasks) {
        return IntStream.range(0,
                tasks.size()).mapToObj((index) -> String.format("%d. %s", index + 1, tasks.get(index).toString()))
                .collect(Collectors.toList());
    }

    /**
     * Displays a list of tasks that is formatted by {@code formatTaskList}.
     *
     * @param tasks List of tasks.
     */
    public void displayTaskList(List<Task> tasks) {
        List<String> toPrint = formatTaskList(tasks);
        formatAndPrint(toPrint);
    }

    /**
     * Displays a list of tasks that is formatted by {@code formatTaskList} along
     * with a search message. Will display a unsuccessful message if task list is
     * empty.
     *
     * @param tasks List of tasks.
     */
    public void displayTaskListSearch(List<Task> tasks) {
        if (tasks.isEmpty()) {
            formatAndPrint("No task matched your query!");
            return;
        }
        List<String> toPrint = new ArrayList<>();
        toPrint.add("Here is what I found: ");
        toPrint.addAll(formatTaskList(tasks));
        formatAndPrint(toPrint);
    }

    /**
     * Displays add task message.
     *
     * @param task Task that was added.
     * @param taskListSize Size of TaskList.
     */
    public void displayAddTaskMessage(Task task, int taskListSize) {
        displayUpdateMessage(task, "Task added: ", Optional.of(taskListSize));
    }

    /**
     * Displays delete task message.
     *
     * @param task Task that was deleted.
     * @param taskListSize Size of TaskList.
     */
    public void displayDeleteTaskMessage(Task task, int taskListSize) {
        displayUpdateMessage(task, "Task deleted: ", Optional.of(taskListSize));
    }

    /**
     * Displays mark task message.
     *
     * @param task Task that was marked.
     */
    public void displayMarkTaskMessage(Task task) {
        displayUpdateMessage(task, "I have marked this task as done: ", Optional.empty());
    }

    /**
     * Displays unmark task message.
     *
     * @param task Task that was unmarked.
     */
    public void displayUnmarkTaskMessage(Task task) {
        displayUpdateMessage(task, "I have unmarked the completion of this task: ", Optional.empty());
    }

    /**
     * Displays exit message.
     */
    public void displayExitMessage() {
        formatAndPrint("Bye bye");
    }

    /**
     * Displays unknown command message.
     *
     * @param input Unknown command text.
     */
    public void displayUnknownCommandMessage(String input) {
        formatAndPrint("Unknown command: " + input);
    }

}
