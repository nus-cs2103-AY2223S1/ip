public class AddSavedInputCommand extends Command {

    private final String task;
    private final Duke.Instructions type;
    private final String timing;
    private final boolean done;

    public AddSavedInputCommand(String task, boolean done) {
        super(false);
        this.task = task;
        this.type = Duke.Instructions.todo;
        this.timing = "";
        this.done = done;
    }

    public AddSavedInputCommand(String task, Duke.Instructions instruction, String timing, boolean done) {
        super(false);
        this.type = instruction;
        this.task = task;
        this.timing = timing;
        this.done = done;
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
    }
}
