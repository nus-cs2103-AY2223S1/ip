package duke;

/**
 * The class with deals with user interaction.
 */
public class Ui {
    public static final String LINE_BREAK = "____________________________________________________________";

    /**
     * Prints a welcome message when the program starts.
     */
    public void welcome() {
        System.out.println("_____________   _       _______________\n|___   __||  | | |     | |__  __| ____|"
                + "\n    | |  | __ ||  \\   /  |  | | | |___\n _  | | | |__| |   \\_/   |  | | |  ___|"
                + "\n| |_| | |  __  | |\\   /| |__| |_| |___\n|_____| |_|  |_|_| \\_/ |_|______|_____|");
        System.out.println("Hi, I'm Jamie.\nWhat do you want to do?\n"
                + "For dates and time, please enter in the format:\n"
                + "dd/MM/yyyy HHmm eg. 29/10/2022 0000");
    }

    /**
     * The method to list out the tasks that are present in taskList.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println("Here are your tasks");
        System.out.println(LINE_BREAK);

        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.getTask(i - 1);
            System.out.println(i + "." + t);
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints out the message when a task is added.
     * @param task The task that is added.
     */
    public void addTasksUi(Task task) {
        System.out.println(LINE_BREAK);
        System.out.println("Got it! I've added this task:\n  "
                + task);
    }

    /**
     * Prints out the message for when a task is being marked.
     * @param marking The marking that is being done.
     * @param task The task that is being marked.
     */
    public void markingUi(String marking, Task task) {
        System.out.println(LINE_BREAK);
        if (marking.equals("mark")) {
            System.out.println("Ok! I've marked it as done.\n  "
                    + task.toString());
        } else {
            System.out.println("Ok! I've marked it as undone.\n  "
                    + task.toString());
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints out the message for when a task is deleted.
     * @param task The task that is deleted.
     */
    public void deleteUi(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Ok, I've removed this task:\n  "
                + task.toString());
    }

    /**
     * Prints out the message that displays the number of tasks in the list of tasks.
     * @param tasks The list of tasks.
     */
    public void numberOfTasksUi(TaskList tasks) {
        System.out.println("You now have " + tasks.size() + " tasks in the list :)");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints out an error message when there are missing numbers.
     * @param command The command that was called.
     */
    public void missingNumberAfterCommand(String command) {
        System.out.println("Please enter numbers after " + command);
    }

    /**
     * Prints out an error message when an invalid command is called.
     */
    public void invalidCommand() {
        System.out.println("Please enter a valid command");
    }

    /**
     * Prints out an error message when something is missing in the description of a task.
     * @param task The task that has a missing description.
     */
    public void someThingMissingError(String task) {
        System.out.println("Something is missing in the description of this + " + task + "! >:(");
    }

    /**
     * The error message for when there is no such task.
     */
    public void noSuchTaskError() {
        System.out.println(LINE_BREAK);
        System.out.println("There's no such task!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a bye message when the program ends.
     */
    public void bye() {
        System.out.println("bye :(");
    }
}
