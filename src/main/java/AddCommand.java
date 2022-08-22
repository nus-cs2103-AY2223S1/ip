public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        Command.taskList.addTask(this.task);
        Command.ui.printTaskListChange("Got it. I've added this task:", this.task, Command.taskList);
    }
}
