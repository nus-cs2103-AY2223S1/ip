public class UnmarkCommand extends Command {
    private int taskIndex;

    public static String getFormat() {
        return "unmark <Integer>";
    }
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(this.taskIndex);
        System.out.println("Sure! I've unmarked this task: ");
        System.out.println("   " + task);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
