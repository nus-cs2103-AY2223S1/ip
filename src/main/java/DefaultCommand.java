public class DefaultCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Try typing something else!");
    }

    @Override
    boolean isExit() {
        return false;
    }
}
