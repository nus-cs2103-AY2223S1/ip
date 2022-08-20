package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private static final String lineBreakBefore =
            "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
            + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private static final String lineBreakAfter =
            "______________________________________________________"
            + "______________________________________________________";
    private static final String logo =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String greeting1 = "Hello! I'm duke.Duke.";
    private static final String greeting2 = "What can I do for you?";

    private Scanner commandInput;

    public Ui() {
        this.commandInput = new Scanner(System.in);
    }


    public void greet() {
        System.out.println(logo + "\n" + greeting1);
        System.out.println(greeting2);
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
        System.out.println(lineBreakBefore);
        String command = commandInput.nextLine();
        System.out.println(lineBreakAfter);
        return command;
    }
}
