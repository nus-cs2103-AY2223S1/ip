public class UnmarkCommand extends Command{
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) throws DukeException{
        ui.unmarkResponse(tasks.unmarkTask(taskIndex));
        st.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
