public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws Duke.DukeException {
        taskList.addTask(this.task);
        ui.line();
        System.out.println("Got it. I've added this task:");
        System.out.println(this.task);
        taskList.printArraySize();
        ui.line();
    }
}
