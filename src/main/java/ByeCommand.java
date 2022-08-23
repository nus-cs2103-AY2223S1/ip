public class ByeCommand extends Command {

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws Duke.DukeException {
        ui.line();
        System.out.println("Bye. Hope to see you again soon!");
        ui.line();
        storage.saveData(taskList);
    }
}
