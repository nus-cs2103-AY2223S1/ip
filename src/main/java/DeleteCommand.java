public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(Duke duke) {
        duke.deleteTask(index);
    }
}
