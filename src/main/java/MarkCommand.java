public class MarkCommand extends Command {
    int index;
    boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void run(Duke duke) {
        duke.markTask(index, isDone);
    }
}
