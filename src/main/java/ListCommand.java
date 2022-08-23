public class ListCommand extends Command {

    ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {
        System.out.print(ui.showList(taskList));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
