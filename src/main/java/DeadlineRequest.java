public class DeadlineRequest extends Request {

    private Deadline deadline;
    private TasksList tasksList;
    private static final String DELIMITER = " /by";
    private static final String DEADLINE_MSG = "Got it. I've added this task: \n";

    public DeadlineRequest(TasksList tasksList, String requestCommand) {
        this.tasksList = tasksList;
        this.deadline = createdDeadline(requestCommand);
    }

    //Static as unsafe to call instance method from constructor
    private static Deadline createdDeadline(String requestCommand) {
        String[] splitted = requestCommand.split(DeadlineRequest.DELIMITER, 2);
        return new Deadline(splitted[0], splitted[1]);
    }

    @Override
    public void execute() {
        this.tasksList.addToList(this.deadline);
        StringBuilder sb = new StringBuilder();
        sb.append(DeadlineRequest.DEADLINE_MSG + this.deadline + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        super.printResponse(sb.toString());
    }

}


