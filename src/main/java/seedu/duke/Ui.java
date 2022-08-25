package seedu.duke;
import java.util.Scanner;

public class Ui {
    private Scanner scan = new Scanner(System.in);
    private Parser parser;
    private TaskList tasklist;

    /**
     * A constructor that returns an instance of Ui.
     *
     * @param tasklist
     */
    public Ui(TaskList tasklist) {
        this.parser = new Parser(tasklist);
        this.tasklist = tasklist;
    }

    /**
     * Greets the user and calls the Parser to open a scanner and start
     * taking in user input.
     */
    public void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");

        parser.nextCommand();
    }

    /**
     * Informs the user what task has been deleted and how many tasks are left.
     *
     * @param deleted The deleted task.
     * @param size The number of tasks remaining.
     */
    public static void deleteText(String deleted, int size) {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Noted. I've removed this task: \n"
                        + deleted + "\n"
                        + "Now you have " + size + " tasks in the list. \n"
                        + "____________________________________________________________");
    }

    public static void addText(String added, int size) {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + added + "\n"
                        + "Now you have " + size + " tasks in the list. \n"
                        + "____________________________________________________________");
    }

    public static void taskNotFoundText() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! That task doesn't exist. \n"
                        + "____________________________________________________________");
    }

    public static void emptyDescription(String taskType) {
        if (taskType.equals("Event")) {
            System.out.println(
                    "____________________________________________________________ \n"
                            + "☹ OOPS!!! The description of an Event cannot be empty. \n"
                            + "____________________________________________________________");
        } else {
            System.out.println(
                    "____________________________________________________________ \n"
                            + "☹ OOPS!!! The description of a " + taskType + " cannot be empty. \n"
                            + "____________________________________________________________");
        }
    }

    public static void unknownElement() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! Unknown element in save file. \n"
                        + "____________________________________________________________");
    }

    public static void unableToAdd() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! Unable to add task. \n"
                        + "____________________________________________________________");
    }

    public static void wrongDateFormat() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! The deadline must be in yyyy-MM-dd hh:mm AM/PM format. \n"
                        + "____________________________________________________________");
    }

    public static void saveError() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! Unable to find/create save file. \n"
                        + "____________________________________________________________");
    }

    public static void unknownCommand() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                        + "____________________________________________________________");
    }

    public static void bye() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon! \n"
                        + "____________________________________________________________");

        System.exit(0);
    }
}
