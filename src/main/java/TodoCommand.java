public class TodoCommand extends Command {
    private String desc;

    public TodoCommand(String input) {
        if (input.isBlank()) {
            throw new BlinkException("Missing description for Todo");
        }
        this.desc = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDos event = tasks.addTodo(this.desc);
        ui.addTask(tasks, event);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
