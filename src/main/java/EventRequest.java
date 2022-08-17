public class EventRequest extends Request {
    private String[] inputArray;
    private TasksList tasksList;
    private static final String DELIMITER = " /at ";
    private static final String EVENT_MSG = "Got it. I've added this task: \n";

    public EventRequest(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("The description of a event cannot be empty!");
        }

        //split again to get date/time
        String[] splitArray = this.inputArray[1].split(EventRequest.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Please enter a starting and ending time for this task!");
        }

        //Make a new event object
        Event event = new Event(splitArray[0], splitArray[1]);
        this.tasksList.addToList(event);
        StringBuilder sb = new StringBuilder();
        sb.append(EventRequest.EVENT_MSG + event + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        super.printResponse(sb.toString());
    }

}
