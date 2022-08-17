public class MarkResponse extends Response{
    private TaskList taskList;
    private String[] inputArr;

    public MarkResponse(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public void action() throws DukeException{
        if (this.inputArr.length < 2) {
            throw new DukeException("Missing task number.");
        }
        try {
            int index = Integer.parseInt(this.inputArr[1]) - 1;
            this.taskList.markDone(index);
            super.printMessage("Nice! I've marked this task as done:" + "\n"
                    + this.taskList.getTask(index) + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
