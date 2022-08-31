public class MarkCommand extends Command {
    public String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskList.get(index).markAsDone();
            ui.showMarkedTask(taskList.get(index));
        } catch (NumberFormatException e) {
            throw new DukeException("Input a valid number!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
