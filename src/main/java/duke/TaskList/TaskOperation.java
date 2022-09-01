package duke.TaskList;

import Ui.Constants;
import duke.Storage.DukeEncoder;

import java.util.ArrayList;

public class TaskOperation {

    /**
     * Add text that user typed to the word list
     *
     * @param task text the user typed
     * @param workList
     */
    public static String add(Task task, ArrayList<Task> workList) {
        workList.add(task);
        // Update data
        DukeEncoder.rewriteList(workList);
        return (Constants.ARROW + "Added task: " + task.toString()) + "\n" +
              ("Now you have " + workList.size() + " task(s) on your list.");
    }

    /**
     * Delete a task
     *
     * @param task text the user typed
     * @param workList
     */
    public static String delete(Task task, ArrayList<Task> workList) {
        workList.remove(task);
        // Update data
        DukeEncoder.rewriteList(workList);
        return (Constants.ARROW + "Deleted task: " + task.toString()) + "\n" +
               ("Now you have " + workList.size() + " task(s) on your list.");
    }

    /**
     * Print all item in the word list
     */
    public static String listItems(ArrayList<Task> workList) {
        String toPrint = Constants.LISTING_MESSAGE + "\n";
        for (int i = 0; i < workList.size(); i++) {
            toPrint += ((i+1) + ") " + workList.get(i).toString()) + "\n";
        }
        return toPrint;
    }
}
