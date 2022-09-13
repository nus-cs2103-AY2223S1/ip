package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

public class UnmarkHandler {
    /**
     * Handles the UNMARK command
     * Unmarks a task as not completed according to its index.
     * @param taskList TaskList containing a list of tasks.
     * @param input Contains index of the task to be marked as not completed.
     */
    public static String getResponse (TaskList taskList, String input) {
        int taskNo = Integer.parseInt(input);
        assert taskNo >= 1: "TaskList empty";
        try {
            Task task = taskList.get(taskNo - 1);
            task.setUndone();
            return ("OK, I've marked this task as not done yet:\n"
                    + "[" + task.getStatusIcon() + "] " + task.getTaskName());
        } catch (IndexOutOfBoundsException e) {
            int taskListSize = taskList.size();
            return (String.format("List size is %s. Please enter a valid input."
                    , taskListSize));
        }
    }
}
