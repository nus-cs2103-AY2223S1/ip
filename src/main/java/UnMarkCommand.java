public class UnMarkCommand extends Command {
    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        System.out.println("\t" + " OK! I've marked this task as not done yet:");
        System.out.println("\t " + task);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
