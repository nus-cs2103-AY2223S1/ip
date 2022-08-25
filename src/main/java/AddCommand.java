public class AddCommand extends Command {

    private String id = "";
    private String content = "";
    private String time = "";

    public AddCommand(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public AddCommand(String id, String content, String time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }
    //for adding new tasks, not for loading
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) throws DukeException{
        Task task = null;
        switch(id) {
        case "T":
            task = new Todo(content, false);
            break;
        case "E":
            task = new Event(content, time, false);
            break;
        case "D":
            task = new Deadline(content, time, false);
            break;
        default:
            throw new DukeException();
        }
        tasks.addTask(task);
        storage.appendToList(task.summary());
        ui.showAdd(id, task, tasks.tasksLeft());

    }

}
