public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        String message = tasks.mark(index - 1);
        ui.mark(message);
        storage.update(tasks.getTasks());
    }
    
}
