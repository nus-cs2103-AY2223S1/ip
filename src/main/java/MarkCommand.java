public class MarkCommand extends Command {

    int index;

    public MarkCommand(int index) {
            this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(index).mark();
        ui.print("I've marked this task as done: \n" + tasks.get(index));
    }

}
