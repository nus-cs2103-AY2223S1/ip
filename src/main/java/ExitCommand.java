public class ExitCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("UwU Byebyeeee! Come back soon... Meowmeow misses you already =^._.^= ");
    }

    @Override
    boolean isExit() {
        return true;
    }
}
