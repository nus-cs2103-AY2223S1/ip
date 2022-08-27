public class MarkAsUndoneCommand extends Command {
    int taskNo;

    public MarkAsUndoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskNo);
        task.markAsUndone();
        System.out.printf("OK, I've marked this task as not done yet: \n" +
                            "%s\n", task);

    }
}
