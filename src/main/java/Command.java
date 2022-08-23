public abstract class Command {

    public boolean isDone() {
        return false;
    }

    public void handle(Storage storage, Ui ui, TaskList taskList) throws Duke.DukeException {};
}
