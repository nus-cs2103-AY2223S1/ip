package duke;

public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Constructor of delete command
     *
     * @param taskNumber task number to be deleted
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task target = tasks.deleteTask(taskNumber);
        storage.save(tasks);
        return ui.showDeleteTask(target, tasks.getSize());
    }

    public boolean isExit() {
        return false;
    }
}
