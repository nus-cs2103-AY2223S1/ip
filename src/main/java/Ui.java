import java.util.Scanner;

public class Ui {
    private final static String lineBreakBefore //Line before Duke is given a command
            = "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
            + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private final static String lineBreakAfter //Line after Duke is given a command
            = "______________________________________________________"
            + "______________________________________________________";
    private final static String logo
            = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private final static String greeting1
            = "Hello! I'm Duke.";
    private final static String greeting2
            = "What can I do for you?";

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
        System.out.println("Good Job! I will mark this task as done:" +
                "\n" + task);
    }

    public void unmark(Task task) {
        System.out.println("Alright, I will mark this task as undone:" +
                "\n" + task);
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
        System.out.println("Understood. I will purge this task from your list:\n" +
                task +
                "\nCurrently, you have " + size + " tasks in your list.");
    }

    public String readCommand() {
        System.out.println(lineBreakBefore);
        String command = commandInput.nextLine();
        System.out.println(lineBreakAfter);
        return command;
    }
}
