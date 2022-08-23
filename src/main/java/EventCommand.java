public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String at;

    public EventCommand(String description, String at) {
        super();
        this.description = description;
        this.at = at;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.at);
        taskList.addTask(event);
        ui.showTaskAdded(event);
        ui.showNumberOfTasks(taskList.numTasks());
    }
}
