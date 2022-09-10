package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

import static duke.exception.ErrorMessage.INVALID_INDEX;
import static duke.exception.ErrorMessage.MISSING_INDEX;

public class MarkCommand implements Command {
    private TaskList tasks;
    private String taskType;
    private String index;

    public MarkCommand(TaskList tasks, String taskType, String index) {
        this.tasks = tasks;
        this.taskType = taskType;
        this.index = index;
    }

    public String execute() throws DukeException {
        String taskIndex = index.trim();
        if (taskIndex.equals("") || !taskIndex.matches("[0-9]+")) { // task description empty or not numeric
            throw new DukeException(MISSING_INDEX, "");
        } else if (Integer.parseInt(taskIndex) > tasks.getSize() || Integer.parseInt(taskIndex) < 1) {
            throw new DukeException(INVALID_INDEX, "");
        }
        if (taskType.equals("mark")) {
            tasks.markAsDone(Integer.parseInt(taskIndex));
            return String.format("mark %s", taskIndex);
        } else if (taskType.equals("unmark")) {
            tasks.markAsUndone(Integer.parseInt(taskIndex));
            return String.format("unmark %s", taskIndex);
        } else {
            Task t = tasks.deleteTaskAtIndex(Integer.parseInt(taskIndex));
            return String.format("delete %s %s",t.getStatusIcon(), t.getDescription());
        }
    }
}
