package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;
import seedu.duke.task.Task;

abstract class MakeTaskCommand extends Command {

    private final String detail;

    MakeTaskCommand(String detail) {
        this.detail = detail;
    }

    /**
     * This method will handle operations that involves making a Task.
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     * @throws DukeException    Thrown when the input is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = makeNewTask(detail, ui);
        checkClashes(newTask, tasks, ui);
        tasks.addTask(newTask);
        ui.appendMessage(ui.getNewTaskMessage());
        ui.appendMessage(newTask.toString());
        storage.updateSave(tasks);
    }

    abstract Task makeNewTask(String detail, Ui ui) throws DukeException;

    void checkClashes(Task task, TaskList tasks, Ui ui) throws DukeException {
        for (int i = 1; i <= tasks.numOfTasks(); i++) {
            if (tasks.fetchTask(i).isClash(task)) {
                throw new DukeException(ui.getClashingEventErrorMessage(tasks.fetchTask(i), task));
            }
        }
    }
}
