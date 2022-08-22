public class MarkCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    public MarkCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public String action() throws DukeException{
        if (this.inputArr.length < 2) {
            throw new DukeException("Missing task number.");
        }
        try {
            int index = Integer.parseInt(this.inputArr[1]) - 1;
            Task task = this.taskList.markDone(index);
            return ("Nice! I've marked this task as done:" + "\n"
                    + task + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
