import duke.exceptions.DukeException;

public class DeleteCommand extends Command {

    private final String indexString;

    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(indexString);
        storage.saveData(tasks);
        ui.printMessage("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + tasks.size() + " tasks.");
    }

    @Override
    public String getCommand() {
        return "delete";
    }
}
