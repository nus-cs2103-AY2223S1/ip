public abstract class AddCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        this.add(tasks); //add into tasks
        ui.sayAdded(tasks.arr);
        storage.overwrite(); //overwrite
    }
    public abstract void add(TaskList tasks) throws DukeException;
    @Override
    public boolean isExit() {
        return false;
    };
}
