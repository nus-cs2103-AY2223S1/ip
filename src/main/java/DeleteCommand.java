import java.io.FileNotFoundException;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(this.input, ui);
            storage.removeFromFile(this.input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
