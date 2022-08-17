public class EventResponse extends Response {
    private TaskList taskList;
    private String input;

    public EventResponse(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    @Override
    public void action() {
        String[] inputArr = this.input.split(" /at ", 2);
        String task = inputArr[0];
        String date = inputArr[1];
        Event event = new Event(task, date);
        this.taskList.addTask(event);
        super.printMessage("Got it. I've added this task: " + "\n"
                + event + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}