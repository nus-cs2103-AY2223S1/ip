public class AddCommand extends Command {

    private final String task;
    private final Duke.Instructions type;
    private final String timing;
    private final boolean done;

    public AddCommand(String task) {
        super(false);
        this.task = task;
        this.type = Duke.Instructions.todo;
        this.timing = "";
        this.done = false;
    }


    public AddCommand(String task, Duke.Instructions instruction, String timing) {
        super(false);
        this.type = instruction;
        this.task = task;
        this.timing = timing;
        this.done = false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case todo:
            newTask = new ToDos(task, done);
            break;
        case deadline:
            newTask = new Deadlines(task, timing, done);
            break;
        case event:
            newTask = new Events(task, timing, done);
            break;
        default:
            newTask = null; //Should never be reached
        }
        taskList.add(newTask);
        ui.showAdd(taskList, newTask);
        new SaveCommand().execute(taskList, ui, storage);
    }
}
