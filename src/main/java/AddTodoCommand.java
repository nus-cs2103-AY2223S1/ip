public class AddTodoCommand extends  Command {
    public static final String ADD_TODO = "todo";
    public final String moreInfo;

    public AddTodoCommand(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(moreInfo);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks);
        storage.savesFile()
    }
}
