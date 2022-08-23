public class MarkDoneCommand extends Command {
    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }
    public void handle(Storage storage, Ui ui, TaskList taskList) throws Duke.DukeException {
        Task t = taskList.getTask(index);
        t.markDone();
        ui.line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        ui.line();
    }
}
