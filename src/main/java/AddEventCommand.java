public class AddEventCommand extends Command {
    private String description;
    private String by;

    public AddEventCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Events(description, by);
        tasks.add(task);
        ui.addSuccess(task,tasks);
        storage.save(tasks);
    }
}
