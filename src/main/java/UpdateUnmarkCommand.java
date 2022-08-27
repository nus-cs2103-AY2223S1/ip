import java.io.IOException;

public class UpdateUnmarkCommand extends UpdateCommand {
    private UpdateUnmarkCommand(String command, Task task, int taskIndex) {
        super(command, task, taskIndex);
    }

    public static UpdateUnmarkCommand of(String command, TaskList taskList) throws IllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Parser.getTaskIndex(command, taskList);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a number to unmark a task.\n");
        }

        Task task;
        try {
            task = taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to unmark a task.\n", taskList.size())
            );
        }
        return new UpdateUnmarkCommand(command, task, taskIndex);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        taskList.unmark(super.taskIndex, storage);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            this.execute(taskList, storage);
        } catch (IOException e) {
            ui.println(e.getMessage());
        }
        ui.printWithDivider(String.format("OK, I've marked this task as not done yet:\n  %s", super.task.toString()));
    }
}
