public class DeadlineResponse extends Response{
    private TaskList taskList;
    private String[] inputArr;

    public DeadlineResponse(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public void action() throws DukeException{
        if (this.inputArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] descriptionDate = this.inputArr[1].split(" /by ", 2);
        if (descriptionDate.length < 2) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }
        String task = descriptionDate[0];
        String date = descriptionDate[1];
        Deadline event = new Deadline(task, date);
        this.taskList.addTask(event);
        super.printMessage("Got it. I've added this task: " + "\n"
                + event + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}