package duke.logic.task;
import java.util.ArrayList;

import duke.storage.DukeEncoder;
import duke.ui.Constants;

/**
 * Represents operation on task.
 */
public class TaskOperation {

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
        return (Constants.ARROW + "Deleted task: " + task.toString()) + "\n"
                + ("Now you have " + workList.size() + " task(s) on your list.");
    }
}
