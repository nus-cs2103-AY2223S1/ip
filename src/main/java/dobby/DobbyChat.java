package dobby;

import dobby.tasks.Task;

/**
 * DobbyChat is a class that handles various outputs Dobby will give.
 */
public class DobbyChat {
    private static final String hello = "Hello! I'm Dobby\n"
            + "\t" + "How can I help you?";
    private static final String bye = "Byebye. Dobby will miss you!";
    private static final String line = "\t----------------------------------------------";

    /**
     * Prints messages in a specific format.
     *
     * @param c String to be printed.
     */
    private static void printChat(String c) {
        System.out.println(line);
        System.out.println("\t" + c);
        System.out.println(line);
    }

    /**
     * Prints messages in a specific format.
     *
     * @param s String to be printed.
     */
    public static void echo(String s) {
        printChat(s);
    }

    /**
     * Prints message to welcome users.
     */
    public static void sayHello() {
        printChat(hello);
    }

    /**
     * Prints message to bid farewell to users.
     */
    public static void sayBye() {
        printChat(bye);
    }

    /**
     * Prints message when users mark tasks.
     *
     * @param s task to be marked
     */
    public static void marked(String s) {
        String marked = "Well done master! Dobby has marked the following task as done: \n\t";
        String toPrint = marked + s;
        printChat(toPrint);
    }

    /**
     * Prints message when users unmark tasks.
     *
     * @param s task to be unmarked
     */
    public static void unmarked(String s) {
        String unmarked = "OK, Dobby will take care of\n\t";
        String toPrint = unmarked + s;
        printChat(toPrint);
    }

    /**
     * Prints message when users add tasks and shows how many tasks are in in the list.
     *
     * @param task task to be added
     * @param list list where task is added to
     */
    public static void added(Task task, DobbyList list) {
        String taskString = task.toString() + "\n\t";

        String accept = "Yes master, Dobby will add the following to the list: \n\t";
        String length = "\n\tYou now have " + list.getLength() + " task(s) left.\n";
        printChat(accept + taskString + length);
    }

    /**
     * Prints message when users delete task and shows how many tasks are left in list
     *
     * @param task task to be deleted
     * @param list list where task is deleted from
     */
    public static void deleted(Task task, DobbyList list) {
        if (list.isEmpty()) {
            printChat("There is no task left for Dobby to delete, YAYYYYY");
        } else {
            String taskString = task.toString() + "\n\t";

            String deleted = "Task deleted! Less work for master! Dobby is HAAAAAPPY!\n\n\t"
                    + "Dobby has removed this task: \n\t";
            String length = "\n\tYou now have " + (list.getLength() - 1) + " task(s) in the list.\n";
            printChat(deleted + taskString + length);
        }
    }

    /**
     * Error message when no date is added to deadline.
     */
    public static void noDeadlineDate() {
        printChat("Please add the deadline after the task name using /by");
    }

    /**
     * Error message when no date is added to event.
     */
    public static void noEventDate() {
        printChat("Please add the event date after the task name using /at");
    }

    /**
     * Error message when no date is added after task description.
     */
    public static void noDate() {
        printChat("Please add the date after the task name using /at or /by");
    }
    /**
     * Error message when false command is entered.
     */
    public static void unknown() {
        printChat("Dobby doesn't understand what you're saying...");
    }

    /**
     * Error message when no task number is added to mark, unmark or delete command.
     */
    public static void noTaskNumber() {
        printChat("You need to tell Dobby the task number...");
    }

    /**
     * Error message when no task description is added after command.
     */
    public static void noTaskDesc() {
        printChat("You need to tell Dobby the task you wish to add...");
    }

    /**
     * Error message when user enters non-number after mark, unmark or delete command.
     */
    public static void noNumber() {
        printChat("Dobby doesn't see a number...");
    }

    /**
     * Error message when user runs commands on a task number not availble on the task list.
     */
    public static void tooLittleTasks() {
        printChat("There aren't that many tasks...would you like Dobby to add more?");
    }

    /**
     * Error message when user runs commands on an empty task list.
     */
    public static void noTaskToDelete() {
        printChat("Dobby doesn't see any task to delete");
    }

    /**
     * Error message when user enters a negative number after mark, unmark or delete command.
     */
    public static void wrongTaskNumber() {
        printChat("Dobby only knows numbers more than 0...");
    }

    /**
     * Error message when user tries to mark an already marked task.
     */
    public static void alreadyMarked() {
        printChat("Dobby has already marked this task done!");
    }

    /**
     * Error message when user tries to unmark an already unmarked task.
     */
    public static void alreadyUnmarked() {
        printChat("Master has never marked this task done before...");
    }

    /**
     * Error message when the file containing past saved task list has tasks in a wrong format.
     */
    public static void wrongTaskFormat() {
        printChat("Dobby doesn't understand what's written here...");
    }

    /**
     * Message printed when the file containing past saved task list is empty.
     */
    public static void listEmpty() {
        printChat("No tasks left, Dobby is FREEEEEEEEEEEE!");
    }

    /**
     * Error message when user enters dates in the wrong format after the event/date command.
     */
    public static void wrongDateFormat() {
        printChat("Master, please write dates using the YYYY-MM-DD HHMM format");
    }

    public static void noFileToLoadFrom() {
        printChat("Dobby can't find any previous tasks to load from");
    }
}