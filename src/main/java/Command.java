abstract class Command {


    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExit() {
        return false;
    }
}
