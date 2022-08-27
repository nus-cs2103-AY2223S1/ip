public class MarkAsDoneCommand extends Command {
    int taskNo;

    public MarkAsDoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskNo);
        task.markAsDone();
        System.out.printf("Nice! I've marked this task as done: \n" +
                "%s\n", task);
    }
}
