public class MarkCommand extends Command{
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) throws DukeException{
        ui.markResponse(tasks.markTask(taskIndex));
        st.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
