package seedu.duke;

public class Ui {
    private Parser parser;

    /**
     * A constructor that returns an instance of Ui.
     *
     * @param tasklist The TaskList instance for this instance of Duke.
     */
    public Ui(TaskList tasklist) {
        this.parser = new Parser(tasklist);
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

    /**
     * Tells the user what Task was added and how many Tasks are in the list.
     *
     * @param added Description of the Task added.
     * @param size The total number of tasks.
     */
    public static void addText(String added, int size) {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + added + "\n"
                        + "Now you have " + size + " tasks in the list. \n"
                        + "____________________________________________________________");
    }

    /**
     * Informs the user that the task they tried to access does not exist.
     */
    public static void taskNotFoundText() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! That task doesn't exist. \n"
                        + "____________________________________________________________");
    }

    /**
     * Informs the user that the format of their command was incorrect.
     *
     * @param taskType The type of Task the user tried to create.
     */
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

    /**
     * Tells the user that Duke does not recognise the information in the save file.
     */
    public static void unknownElement() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! Unknown element in save file. \n"
                        + "____________________________________________________________");
    }

    /**
     * Tells the user that Duke was unable to add their task to the list.
     */
    public static void unableToAdd() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! Unable to add task. \n"
                        + "____________________________________________________________");
    }

    /**
     * Tells the user that their date format is incorrect.
     */
    public static void wrongDateFormat() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! The deadline must be in yyyy-MM-dd hh:mm AM/PM format. \n"
                        + "____________________________________________________________");
    }

    /**
     * Tells the user that Dukes was unable to find or create a save file.
     */
    public static void saveError() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! Unable to find/create save file. \n"
                        + "____________________________________________________________");
    }

    /**
     * Tells the user that their input was not recognised as a command.
     */
    public static void unknownCommand() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                        + "____________________________________________________________");
    }

    /**
     * Bids farewell to the user and ends the program.
     */
    public static void bye() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon! \n"
                        + "____________________________________________________________");

        System.exit(0);
    }
}
