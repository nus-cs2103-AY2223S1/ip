package duke.handlers;

import duke.models.TaskList;

public class DeleteTaskCommand implements DukeCommand {
    public String run (TaskList taskList, String content) {
        try {
            int taskNum = Integer.parseInt(content);
            return "Task deleted:\n" + taskList.DeleteTask(taskNum - 1);
        } catch (NumberFormatException e) {
            return "Task index is not a number!\n";
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!\n";
        }
    }
}
