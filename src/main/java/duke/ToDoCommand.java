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
        String addTaskMessage = ui.showAddTask(todo, tasks.getSize());
        if (tasks.checkDuplicates(todo)) {
            return addTaskMessage + "\n" + ui.showDuplicateMessage();
        } else {
            return addTaskMessage;
        }
    }

    public boolean isExit() {
        return false;
    }
}
