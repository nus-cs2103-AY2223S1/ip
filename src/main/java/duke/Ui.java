package duke;

import java.util.List;


/**
 * Encapsulates a class extracted from the main logic to deal with the user interface through the command line
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;





    /**
     * Prints the ending message
     * @return
     */
    public String goodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads the User's input
     * @return The String representation of the user's input
     */


    /**
     * Prints the appropriate message for adding a Task
     * @param task The task added
     * @param size The current number of tasks
     */
    public String showAddCommand(Task task,int size) {
        return "Got it. I've added this task:\n " + task.toString() + "\nNow you have " + size +" tasks in the list.";
    }

    /**
     * Prints the appropriate message for deleting a Task
     * @param task The task deleted
     * @param size The current number of tasks
     */
    public String showDelete(Task task,int size) {
        return " Noted. I've removed this task:\n" + " " + task.toString() +"\nNow you have " + size +" tasks in the list." ;
    }

    /**
     * Prints the appropriate message for marking a Task
     * @param task The task to be marked
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done:" + " " + task.toString();
    }

    /**
     * Prints the appropriate message for unmarking a Task
     * @param task The task to be unmarked
     */
    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet:" + " " + task.toString();
    }

    public String showFindResult(List<Integer> indexList, TaskList taskList) throws DukeException {
        String returnString = "Here are the matching tasks in your list:";
        for (Integer index : indexList) {
            returnString += "\n" + taskList.getTask(index).toString();
        }
        return returnString;
    }



}
