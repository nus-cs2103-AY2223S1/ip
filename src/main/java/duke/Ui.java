package duke;

import java.util.Scanner;

import duke.task.Task;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import java.util.NoSuchElementException;

public class Ui {
    private Scanner scanner;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String PADDING = "  ";
    private static final String DIVIDER = "    ____________________________________________________________";

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    String readCommand() throws DukeException {
        try {
            return this.scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeException("Can't read command!", e);
        }
    }

    String leftPad(String text) {
        return PADDING + text;
    }

    public void formatAndPrint(List<? extends String> texts) {
        System.out.println(DIVIDER);
        texts.forEach((text) -> System.out.println(leftPad(leftPad(" " + text))));
        System.out.println(DIVIDER);
    }

    void formatAndPrint(String text) {
        formatAndPrint(List.of(text));
    }

    void displayErrorMessage(String errorMessage) {
        formatAndPrint("Error! " + errorMessage);
    }

    void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        String[] messages = { "Hello! I'm Duke.", "What can I do for you?" };
        formatAndPrint(List.<String>of(messages));
    }

    void displayUpdateMessage(Task task, String updateMessage, Optional<Integer> taskListSize) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add(updateMessage);
        toPrint.add(leftPad(task.toString()));
        taskListSize.ifPresent((size) -> toPrint.add("There are now " + size + " tasks in the list."));
        formatAndPrint(toPrint);
    }

    public void displayAddTaskMessage(Task task, int taskListSize) {
        displayUpdateMessage(task, "Task added: ", Optional.of(taskListSize));
    }

    public void displayDeleteTaskMessage(Task task, int taskListSize) {
        displayUpdateMessage(task, "Task deleted: ", Optional.of(taskListSize));
    }

    public void displayMarkTaskMessage(Task task) {
        displayUpdateMessage(task, "I have marked this task as done: ", Optional.empty());
    }

    public void displayUnmarkTaskMessage(Task task) {
        displayUpdateMessage(task, "I have unmarked the completion of this task: ", Optional.empty());
    }

    public void displayExitMessage() {
        formatAndPrint("Bye bye");
    }

    public void displayUnknownCommandMessage(String input) {
        formatAndPrint("Unknown command: " + input);
    };

}
