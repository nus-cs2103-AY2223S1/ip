public abstract class Command {
    Storage storage;
    UI ui;
    TaskList taskList;

    public Command(Storage storage, UI ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    public Command() {

    }

    abstract void execute(Storage storage, UI ui, TaskList taskList) throws DukeException;
}
