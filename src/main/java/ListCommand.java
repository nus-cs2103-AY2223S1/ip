public class ListCommand extends Command{

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.print();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
