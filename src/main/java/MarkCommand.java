public class MarkCommand extends Command {
    private int taskIndex;

    MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws DukeException {
        Task markedTask = Command.taskList.markTask(this.taskIndex);
        Command.ui.printMessages(new String[]{"Nice! I've marked this task as done:", markedTask.toString()});
    }
}