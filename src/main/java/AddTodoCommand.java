public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDos(description);
        tasks.add(task);
        ui.addSuccess(task,tasks);
        storage.save(tasks);
    }
}
