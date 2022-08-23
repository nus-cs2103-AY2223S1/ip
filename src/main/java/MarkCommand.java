import java.io.IOException;

public class MarkCommand extends Command{
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.input.matches("\\d+")) {
            int markIndex = Integer.parseInt(this.input) - 1;
            if (markIndex < 0 || markIndex >= taskList.length()) {
                throw new DukeException(String.format("There is no task with index %d", markIndex + 1));
            } else {
                Task markTask = taskList.index(markIndex);
                if (markTask.isDone) {
                    throw new DukeException("This task is already marked as done:\n" + markTask.toString());
                } else {
                    markTask.markDone();
                    ui.print("Nice! I've marked this task as done:\n" + markTask.toString());
                }
            }
        } else {
            throw new DukeException(this.input + " is not an integer.");
        }
        storage.saveTasks(taskList);
    }
}
