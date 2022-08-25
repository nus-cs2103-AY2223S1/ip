public class DeleteCommand extends Command {
    private int taskNum;

    DeleteCommand(int oneBasedIndex) {
        this.taskNum = taskNum;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size() + 1) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }

        Task deletedTask = tasks.get(taskNum - 1);
        tasks.delete(taskNum - 1);
        ui.printMessage("\tNoted. I've removed this task:\n\t" +
                deletedTask.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAllTasks());
    }
}
