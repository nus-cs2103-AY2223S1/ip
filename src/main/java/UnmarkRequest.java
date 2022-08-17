public class UnmarkRequest extends Request{
    private int taskNumber;
    private TasksList tasksList;
    private static final String UNMARK_MSG = "Sure! I've marked this task as not done yet: \n";

    public UnmarkRequest(TasksList tasksList, String requestCommand) {
        this.taskNumber = Integer.valueOf(requestCommand);
        this.tasksList = tasksList;
    }

    @Override
    public void execute() {
        Task markedTask = this.tasksList.markAsUndone(this.taskNumber);
        super.printResponse(UnmarkRequest.UNMARK_MSG + markedTask);
    }

}
