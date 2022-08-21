package duke;

import java.util.Scanner;

import duke.task.Task;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import java.util.NoSuchElementException;

/**
 * Class that handles input and output between the user.
 */
public class Ui {
    public static final String DIVIDER = "    ____________________________________________________________";
    private Scanner scanner;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String PADDING = "  ";

    /**
     * Constructor for Ui. Initialises input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Read user input.
     * 
     * @return user input.
     * @throws DukeException when error reading input.
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
     * @param text text to pad.
     * @return Left padded text.
     */
    String leftPad(String text) {
        return PADDING + text;
    }

    /**
     * Format texts before displaying them to the user.
     * 
     * @param texts texts to display to the user.
     */
    public void formatAndPrint(List<? extends String> texts) {
        System.out.println(DIVIDER);
        texts.forEach((text) -> System.out.println(leftPad(leftPad(" " + text))));
        System.out.println(DIVIDER);
    }

    /**
     * Format text before displaying it to the user.
     * 
     * @param text text to display to ther user.
     */
    void formatAndPrint(String text) {
        formatAndPrint(List.of(text));
    }

    /**
     * Displays error message to the user.
     * 
     * @param errorMessage message to display.
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
     * @param task task that was added / deleted / updated.
     * @param updateMessage message to display first.
     * @param taskListSize size of TaskList.
     */
    private void displayUpdateMessage(Task task, String updateMessage, Optional<Integer> taskListSize) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add(updateMessage);
        toPrint.add(leftPad(task.toString()));
        taskListSize.ifPresent((size) -> toPrint.add("There are now " + size + " tasks in the list."));
        formatAndPrint(toPrint);
    }

    /**
     * Displays add task message.
     * 
     * @param task task that was added.
     * @param taskListSize size of TaskList.
     */
    public void displayAddTaskMessage(Task task, int taskListSize) {
        displayUpdateMessage(task, "Task added: ", Optional.of(taskListSize));
    }

    /**
     * Displays delete task message.
     * 
     * @param task task that was deleted.
     * @param taskListSize size of TaskList.
     */
    public void displayDeleteTaskMessage(Task task, int taskListSize) {
        displayUpdateMessage(task, "Task deleted: ", Optional.of(taskListSize));
    }

    /**
     * Displays mark task message.
     * 
     * @param task task that was marked.
     * @param taskListSize size of TaskList.
     */
    public void displayMarkTaskMessage(Task task) {
        displayUpdateMessage(task, "I have marked this task as done: ", Optional.empty());
    }

    /**
     * Displays unmark task message.
     * 
     * @param task task that was unmarked.
     * @param taskListSize size of TaskList.
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
     * @param input unknown command text.
     */
    public void displayUnknownCommandMessage(String input) {
        formatAndPrint("Unknown command: " + input);
    };

}
