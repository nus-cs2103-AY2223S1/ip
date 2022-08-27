import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
