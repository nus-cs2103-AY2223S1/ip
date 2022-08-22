public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String action() {
        return this.taskList.toString();
    }
}
