public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        ui.printWithIndent("Got it. I've added this task:");
        ui.printWithIndent(" " + task);
        ui.printTaskCount(taskList.taskCount());
        storage.saveFile(taskList);
    }
}
