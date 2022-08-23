public class DeleteTaskCommand extends Command {
    protected int taskNum;

    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        Task task = tasks.delete(taskNum);
        ui.showcase("Haiya so lazy. Deleted this task:", task.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
