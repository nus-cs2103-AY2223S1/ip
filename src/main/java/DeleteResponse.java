public class DeleteResponse extends Response{
    private TaskList taskList;
    private String[] inputArr;

    public DeleteResponse(TaskList taskList, String[] inputArr) {
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
            Task task = this.taskList.deleteTask(index);
            super.printMessage("Noted. I've removed this task:" + "\n"
                    + task + "\n"
                    + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
