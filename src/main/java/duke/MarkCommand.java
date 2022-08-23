package duke;

public class MarkCommand extends Command{
    int indexToMark;
    MarkCommand(int number) {
        this.indexToMark = number - 1;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(indexToMark);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
