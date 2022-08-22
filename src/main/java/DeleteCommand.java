public class DeleteCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    public DeleteCommand(TaskList taskList, String[] inputArr) {
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
            Task task = this.taskList.deleteTask(index);
            return ("Noted. I've removed this task:" + "\n"
                    + task + "\n"
                    + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
