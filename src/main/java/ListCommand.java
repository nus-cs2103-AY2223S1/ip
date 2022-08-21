public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int i = 0;
        for (Task x : taskList.getTaskList()) {
            ui.printMessage("\t" + (++i) + ".\t " + x.toString());
        }
    }
}
