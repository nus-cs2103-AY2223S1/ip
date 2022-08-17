public class MarkRequest extends Request {
    private int taskNumber;
    private TasksList tasksList;
    private static final String MARK_MSG = "Nice! I've marked this task as done: \n";

    public MarkRequest(TasksList tasksList, String requestCommand) {
        this.taskNumber = Integer.valueOf(requestCommand);
        this.tasksList = tasksList;
    }

    @Override
    public void execute() {
        Task markedTask = this.tasksList.markAsDone(this.taskNumber);
        super.printResponse(MarkRequest.MARK_MSG + markedTask);
    }
}



