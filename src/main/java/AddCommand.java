public abstract class AddCommand extends Command {
    protected Task task;

    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.add(this.task, super.command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        this.execute(taskList);
        ui.printWithDivider(String.format("added: %s", this.task.toString()));
    }
}
