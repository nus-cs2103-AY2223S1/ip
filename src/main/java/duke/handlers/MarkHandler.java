package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

public class MarkHandler {
    /**
     * Handles the MARK command
     * Marks a task as done according to its index.
     * @param taskList TaskList containing a list of tasks.
     * @param input Contains index of the task to be marked as completed.
     */
    public static String getResponse(TaskList taskList, String input) {
        int taskNo = Integer.parseInt(input);
        assert taskNo >= 1: "TaskList empty";
        try {
            Task task = taskList.get(taskNo - 1);
            task.setDone();
            return ("Nice! I've marked this task as done:\n"
                    + "[" + task.getStatusIcon() + "] " + task.getTaskName());
        } catch (IndexOutOfBoundsException e) {
            int taskListSize = taskList.size();
            return (String.format("List size is %s. Please enter a valid input."
                    , taskListSize));
        }
    }
}
