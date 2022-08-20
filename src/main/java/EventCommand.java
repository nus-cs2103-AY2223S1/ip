public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private Task task;
    public EventCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.addTask(task);
        ui.printAddedTask(list, task);
    }
}
