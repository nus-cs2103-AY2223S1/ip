public class ViewCommand extends Command {

    private String keyword;

     public ViewCommand(String keyword) {
         this.keyword = keyword;
     }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
