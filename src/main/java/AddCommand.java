public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        String temp = tasks.addTask(task);
        ui.addResponse(temp, tasks.getSize());
        st.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
