package duke.command;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class to represent a command to change the status of task in the task list.
 */
public class ChangeStatusCommand extends Command {
    private int taskIndex;
    private boolean isDone;

    /**
     * Constructs a ChangeStatusCommand object.
     *
     * @param storage   Storage class to be used
     * @param ui        Ui class to be used
     * @param taskList  TaskList that specified task is in
     * @param taskIndex Index of task in taskList
     * @param isDone    Whether the task is done or not
     */
    public ChangeStatusCommand(Storage storage, Ui ui, TaskList taskList, String input, boolean isDone)
            throws DukeException {
        super(storage, ui, taskList);
        if (input.split(" ").length == 1) {
            throw new DukeException("Please enter a valid task number!");
        }
        this.taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        this.isDone = isDone;
    }

    /**
     * Changes the status of the specified task.
     *
     * @return String representation of the task whose status has been changed
     * @throws DukeException if taskindex is invalid
     */
    @Override
    public String execute() throws DukeException {
        return taskList.changeTaskStatus(taskIndex, isDone);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChangeStatusCommand)) {
            return false;
        }
        ChangeStatusCommand other = (ChangeStatusCommand) o;
        return other.taskIndex == this.taskIndex && other.isDone == this.isDone;
    }
}
