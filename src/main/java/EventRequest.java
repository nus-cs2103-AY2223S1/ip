public class EventRequest extends Request {
    private Event event;
    private TasksList tasksList;
    private static final String DELIMITER = " /at";
    private static final String EVENT_MSG = "Got it. I've added this task: \n";

    public EventRequest(TasksList tasksList, String requestCommand) {
        this.tasksList = tasksList;
        this.event = createdDeadline(requestCommand);
    }

    //Static as unsafe to call instance method from constructor
    private static Event createdDeadline(String requestCommand) {
        String[] splitted = requestCommand.split(EventRequest.DELIMITER, 2);
        return new Event(splitted[0], splitted[1]);
    }

    @Override
    public void execute() {
        this.tasksList.addToList(this.event);
        StringBuilder sb = new StringBuilder();
        sb.append(EventRequest.EVENT_MSG + this.event + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        super.printResponse(sb.toString());
    }

}
