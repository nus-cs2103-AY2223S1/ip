package duke;

public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new ToDo(this.description);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.showAddTask(todo, tasks.getSize());
    }

    public boolean isExit() {
        return false;
    }
}
