public class TodoCommand extends Command{
    private String description;
    
    public static String getFormat() {
        return "todo <String>";
    }
    
    public TodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(this.description);
        tasks.add(todo);
        ui.showAddTask(todo, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
