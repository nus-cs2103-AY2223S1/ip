public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private String description;
    private String duration;

    public EventCommand(String description, String duration) {
        this.description = description;
        this.duration = duration;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Event currEvent = new Event(this.description, this.duration);
        task.addTask(currEvent);
        ui.displayAddTask(currEvent);
        ui.displayNumOfTasks(task.getTaskSize());
        ui.displaySeparator();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
