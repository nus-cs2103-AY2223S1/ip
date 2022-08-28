public class UnmarkCommand extends Command {

    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(index).unmark();
        ui.print("I've marked this task as undone: \n" + tasks.get(index));
    }

}
