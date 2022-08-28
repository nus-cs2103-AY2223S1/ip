package duke;

abstract class TaskListCommand extends Command {
    private final String cmd;

    TaskListCommand(String cmd) {
        this.cmd = cmd;
    }




    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] split = cmd.split(" ");
        if (split.length == 2) {
            try {
                int taskIndex = Integer.parseInt(split[1]);
                if (taskIndex > tasks.numOfTasks() || taskIndex < 1) {
                    throw new DukeException(ui.getTaskListIndexErrorMessage());
                }
                specialisedFunction(tasks,ui, storage, taskIndex);
                storage.updateSave(tasks);
            } catch (NumberFormatException e) {
                throw new DukeException(ui.getInvalidInputMessage());
            }
        } else if (split.length == 1) {
            throw new DukeException(ui.noIndexProvdedErrorMessage());
        } else {
            throw new DukeException(ui.getInvalidInputMessage());
        }
    }

    abstract void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex);
}
