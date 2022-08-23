public class DeleteCommand extends Command {
    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    public void handle(Storage storage, Ui ui, TaskList taskList) throws Duke.DukeException {
        Task t = taskList.deleteTask(this.index);
        ui.line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        taskList.printArraySize();
        ui.line();
    }
}
