package duke;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import java.util.NoSuchElementException;

public class Ui {
    public static final String DIVIDER = "    ____________________________________________________________";
    private Scanner scanner;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String PADDING = "  ";

    public Ui() {
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

    List<String> formatTaskList(List<Task> tasks) {
        List<String> indexedList = IntStream.range(0,
                tasks.size()).mapToObj((index) -> String.format("%d. %s", index + 1, tasks.get(index).toString()))
                .collect(Collectors.toList());
        return indexedList;
    }

    void displayUpdateMessage(Task task, String updateMessage, Optional<Integer> taskListSize) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add(updateMessage);
        toPrint.add(leftPad(task.toString()));
        taskListSize.ifPresent((size) -> toPrint.add("There are now " + size + " tasks in the list."));
        formatAndPrint(toPrint);
    }

    public void displayTaskList(List<Task> tasks) {
        List<String> toPrint = formatTaskList(tasks);
        formatAndPrint(toPrint);
    }

    public void displayTaskListSearch(List<Task> tasks) {
        if (tasks.size() == 0) {
            formatAndPrint("No task matched your query!");
            return;
        }
        List<String> toPrint = new ArrayList<>();
        toPrint.add("Here is what I found: ");
        toPrint.addAll(formatTaskList(tasks));
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
