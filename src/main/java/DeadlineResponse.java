public class DeadlineResponse extends Response{
    private TaskList taskList;
    private String input;

    public DeadlineResponse(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    @Override
    public void action() {
        String[] inputArr = this.input.split(" /by ", 2);
        String task = inputArr[0];
        String date = inputArr[1];
        Deadline event = new Deadline(task, date);
        this.taskList.addTask(event);
        super.printMessage("Got it. I've added this task: " + "\n"
                + event + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}