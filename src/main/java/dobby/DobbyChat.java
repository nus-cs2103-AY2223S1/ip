package dobby;

import dobby.tasks.Task;

/**
 * DobbyChat is a class that handles various outputs Dobby will give.
 */
public class DobbyChat {
    /**
     * Prints messages in a specific format.
     *
     * @param c String to be printed.
     */
    private static void printChat(String c) {
        System.out.println(c);
    }

    /**
     * Prints message to welcome users.
     */
    public static void getHello() {
        printChat("Hello! I'm Dobby\n" + "How can I help you?");
    }

    /**
     * Prints message to bid farewell to users.
     */
    public static void getBye() {
        printChat("Byebye. Dobby will miss you!");
    }

    public static void getCommands() {
        printChat("Here are some commands Dobby understands:\n"
            + "\t1. todo [task name]\n"
            + "\t2. deadline [task name] /by [YYYY-MM-DD HHMM]\n"
            + "\t3. event [task name] /at [YYYY-MM-DD HHMM]\n"
            + "\t4. list\n"
            + "\t5. mark [task number]\n"
            + "\t6. unmark [date]\n"
            + "\t7. delete [task number]\n"
            + "\t8. find [keyword]\n"
            + "\t9. simplify [old command] [new command]\n"
            + "\t10. bye\n");
    }

    /**
     * Prints message when users mark tasks.
     *
     * @param s task to be marked
     */
    public static void marked(String s) {
        String marked = "Well done master! Dobby has marked the following task as done: \n\n\t";
        String toPrint = marked + s;
        printChat(toPrint);
    }

    /**
     * Prints message when users unmark tasks.
     *
     * @param s task to be unmarked
     */
    public static void unmarked(String s) {
        String unmarked = "OK, Dobby will take care of\n\n\t";
        String toPrint = unmarked + s;
        printChat(toPrint);
    }

    /**
     * Prints message when users add tasks and shows how many tasks are in the list.
     *
     * @param task task to be added
     * @param list task list
     */
    public static void added(Task task, DobbyList list) {
        String taskString = task.toString() + "\n\n";

        String accept = "Yes master, Dobby will add the following task to the list: \n\n\t";
        String length = "Master has " + list.getLength() + " task(s) left.";
        printChat(accept + taskString + length);
    }

    /**
     * Prints message when users delete task and shows how many tasks are left in list.
     *
     * @param task task to be deleted
     * @param list task list
     */
    public static void deleted(Task task, DobbyList list) {
        if (list.isEmpty()) {
            printChat("There is no task left for Dobby to delete, YAYYYYY");
        } else {
            String taskString = task.toString() + "\n";

            String deleted = "Task deleted! Less work for master! Dobby is HAAAAAPPY!\n"
                + "Dobby has removed this task: \n\n\t";
            String length = "\nMaster has only " + (list.getLength() - 1) + " task(s) left.";
            printChat(deleted + taskString + length);
        }
    }

    /**
     * Prints string of lists upon users' request
     *
     * @param list task list
     */
    public static void list(DobbyList list) {
        String intro = "Here are the tasks Dobby has found:\n\n\t";
        String listString = list.toString();
        printChat(intro + listString);
    }

    /**
     * Prints string of lists with description containing keyword
     *
     * @param keyword keyword user wishes to find
     * @param list task list
     */
    public static void find(String keyword, DobbyList list) {
        String intro = "Dobby has found the following relevant tasks:\n\n\t";
        String tasksFound = list.find(keyword);
        printChat(intro + tasksFound);
    }

    /**
     * Prints message when user simplifies task command to their desired custom command.
     *
     * @param initialCmd default custom command.
     * @param newCmd user custom command.
     */
    public static void simplified(String initialCmd, String newCmd) {
        String simplified = "Yes master, from now on dobby will do [" + initialCmd + "] when dobby sees [" + newCmd
            + "].";
        printChat(simplified);
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
     * Error message when false command is entered.
     */
    public static void unknown() {
        printChat("Dobby doesn't understand what you're saying...press commands to see all commands that Dobby "
            + "understands!");
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

    /**
     * Message when no previous task is found.
     */
    public static void noFileToLoadFrom() {
        printChat("Dobby can't find any previous tasks to load from");
    }

    /**
     * Error message when user does not input parameter of simplify.
     */
    public static void noCommandToSimplify() {
        printChat("Please tell dobby which command you wish to simplify.");
    }

    /**
     * Error message when user tries to simplify a non-existent command.
     */
    public static void wrongCommandToSimplify() {
        printChat("Dobby doesn't recognize this command, please try simplifying a valid command!");
    }

}
