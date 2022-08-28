public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        try {
            String message = tasks.deleteTask(index - 1);
            ui.delete(tasks.numOfTasks(), message);
            storage.update(tasks.getTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
    }
    
}
