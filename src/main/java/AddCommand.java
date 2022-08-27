public class AddCommand extends Command {
    String input;

    public AddCommand(Storage storage, Ui ui, TaskList taskList, String input) {
        super(storage, ui, taskList);
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        taskList.addTask(Task.createTask(input));
    }
}
