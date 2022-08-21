package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private static final String LINE_BREAK_BEFORE =
            "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
            + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private static final String LINE_BREAK_AFTER =
            "______________________________________________________"
            + "______________________________________________________";
    private static final String LOGO =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String GREETING_ONE = "Hello! I'm duke.Duke.";
    private static final String GREETING_TWO = "What can I do for you?";

    private Scanner commandInput;

    public Ui() {
        this.commandInput = new Scanner(System.in);
    }


    public void greet() {
        System.out.println(LOGO + "\n" + GREETING_ONE);
        System.out.println(GREETING_TWO);
    }

    public void list() {
        System.out.println("Here are the current tasks in your list:");
    }

    public void mark(Task task) {
        System.out.println("Good Job! I will mark this duke.task as done:" + "\n" + task);
    }

    public void unmark(Task task) {
        System.out.println("Alright, I will mark this duke.task as undone:" + "\n" + task);
    }



    public void showDukeException(String exception) {
        System.out.println(exception);
    }

    public void finalGoodbye() {
        commandInput.close();
        System.out.println("Goodbye. Call for me again when you need me!");
    }

    public void addTask(Task task, int size) {
        System.out.println("Adding to Tasks:" + "\n"
                + task
                + "\nYou have " + size + " tasks in the list.");
    }

    public void delete(Task task, int size) {
        System.out.println("Understood. I will purge this duke.task from your list:\n" + task
                + "\nCurrently, you have " + size + " tasks in your list.");
    }

    public String readCommand() {
        System.out.println(LINE_BREAK_BEFORE);
        String command = commandInput.nextLine();
        System.out.println(LINE_BREAK_AFTER);
        return command;
    }
}
