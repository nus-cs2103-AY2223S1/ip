public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        super(false);
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(deadline);
        ui.printAddTaskReply(deadline.toString(), taskList.getNumOfTask());
        storage.overwriteFile(taskList.toFile());
    }

}

