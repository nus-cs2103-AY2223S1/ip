public class DeleteCommand extends Command {

    protected int index;
    private final String MESSAGE = "\tNoted. I've remove this task: ";

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        storage.saveTaskList(taskList);
        String text = MESSAGE + "\n\t" + task.toString() +
                "\n" + taskList.displayNumTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}