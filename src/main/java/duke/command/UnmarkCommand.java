package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    public UnmarkCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public String action() throws DukeException {
        if (this.inputArr.length < 2) {
            throw new DukeException("Missing task number.");
        }
        try {
            int index = Integer.parseInt(this.inputArr[1]) - 1;
            Task task = this.taskList.unmarkDone(index);
            return ("OK, I've marked this task as not done yet:" + "\n"
                    + task + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        }
    }
}
