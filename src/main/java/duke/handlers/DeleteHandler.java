package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

public class DeleteHandler {
    /**
     * Handles the DELETE command.
     * Deletes a task from TaskList.
     * @param taskList TaskList containing the task to be deleted.
     * @param input Contains index of the task to be deleted.
     */
    public static String getResponse(TaskList taskList, String input) {
        int taskNo = Integer.parseInt(input);
        assert taskNo >= 1: "TaskList empty";
        try {
            Task task = taskList.get(taskNo - 1);
            taskList.remove(taskNo - 1);
            return ("Noted. I've removed this task:\n" + task.toString()
                    + "\nNow you have " + taskList.size() + " tasks left in the list.");
        } catch (IndexOutOfBoundsException e) {
            int taskListSize = taskList.size();
            return (String.format("List size is (%s). Please enter a valid input"
                    , taskListSize));
        }
    }
}
