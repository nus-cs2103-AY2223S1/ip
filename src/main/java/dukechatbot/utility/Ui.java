package dukechatbot.utility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import dukechatbot.dukeexception.DukeException;




/**
 * The Ui class encapsulates operations that
 * interact with the client during the running of the program.
 */
public class Ui {
    /**
     * Defines the array list that is associated with the program run.
     */
    private ArrayList<Task> taskArrayList;

    /**
     * Constructs the instance of Ui to be used for the run of the program.
     * @param taskArrayList the array list to be associated with the program run.
     */
    public Ui(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Prints out logo and greets user upon program run.
     */
    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo
                + "Hello I'm Duke, what can I do for you?";
    }

    /**
     * Says goodbye to the user when the program run is terminated by user.
     */
    public String bye() {
        try {
            Storage.save(this.taskArrayList);
        } catch (IOException ioe) {
            return "Failed to save";
        }
        return "\tBye. Hope to see you soon again!";
    }

    /**
     * Tells the user that the file has failed to be loaded.
     * @throws IOException when called to show that the file
     *                     has failed to be loaded to the user.
     */
    public void showLoadingError() throws IOException {
        throw new IOException("File Loading Error!");
    }

    /**
     * Shows success of adding task into the task list when user tries to add a task.
     * @param t the task that the user has told Duke to add into their task list.
     */
    public String added(Task t) {
        return "Got it. I've added this task: \n" + t.toString()
            + "\n\tNow you have " + this.taskArrayList.size() + " task(s) in the list.";
    }
    /**
     * Shows success of marking the task in the task list as done.
     * @param t the task that the user has told Duke to mark as done.
     */
    public String marked(Task t) {
        return "Nice! I have marked this task as done: \n\t" + t.toString();
    }

    /**
     * Shows the success of marking the task as not done
     * to the user.
     * @param t the task that the user has told Duke to mark as not done.
     */
    public String unmarked(Task t) {
        return "OK, I've marked this task as not done yet: " + "\n\t" + t.toString();
    }

    /**
     * Shows the success of removing the task from the task list.
     * @param t the task that the user has told Duke to remove.
     * @param taskArrayList the task list associated that is to show the
     *           number of tasks left in the list.
     */
    public String removed(Task t, ArrayList<Task> taskArrayList) {
        String response = ("\tNoted. I've removed this task:")
                    + "\t\t" + t.toString() + ("\tNow you have " + taskArrayList.size() + " task(s) in the list.");
        return response;
    }

    /**
     * Tells user that their input is missing the required
     * time components of the instance of the classes that needs them.
     * @throws DukeException when called.
     */
    public void showTimeMissingError() throws DukeException {
        throw new DukeException(
                "☹ OOPS!!! Associated time for event or deadline cannot be empty.");
    }

    /**
     * Tells user when they failed to give a description of the task they wish to add.
     * @throws DukeException when called.
     */
    public void showDescEmptyError(String str) throws DukeException {
        throw new DukeException(""
                + "☹ OOPS!!! The description of a " + str
                + " cannot be empty.");
    }

    /**
     * Tells user that the input they gave is unknown to Duke.
     * @throws DukeException when called.
     */
    public void showUnknownCommandError() throws DukeException {
        throw new DukeException(
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * lists out the tasks in the task list to the user.
     * @return the list of things in the task list in the defined format.
     */
    public String listOut() {
        if (this.taskArrayList.size() == 0) {
            return "You have no tasks.";
        }
        int count = 1;
        StringBuilder response = new StringBuilder();
        response.append(("Here are the tasks in your list: "));
        for (Iterator<Task> it = this.taskArrayList.iterator(); it.hasNext();) {
            Task curr = it.next();
            response.append("\n\t").append(count).append(". ").append(curr.toString());
            count++;
        }
        return response.toString();
    }

    /**
     * lists out the matching tasks in the task list that meets
     * the user's find query.
     * @param taskArrayList containing the tasks store in the task list.
     */
    public String listMatch(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 0) {
            return "You have no tasks.";
        }
        int count = 1;
        StringBuilder response = new StringBuilder();
        response.append(("\tHere are the matching tasks in your list: "));
        for (Iterator<Task> it = taskArrayList.iterator(); it.hasNext();) {
            Task curr = it.next();
            response.append("\t\t").append(count).append(". ").append(curr.toString());
            count++;
        }
        return response.toString();
    }

    /**
     * Tells user that user is trying to add a duplicate task.
     * @param t the task that is a duplicate.
     * @return the message to tell user the task was a duplicate.
     */
    public String foundDuplicate(Task t) {
        return "Sorry! " + t.toString() + " is a duplicate of an item in the current task list.";
    }

}
