public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private Task task;
    public DeadlineCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.addTask(task);
        ui.printAddedTask(list, task);
    }
}
