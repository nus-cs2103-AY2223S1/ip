public class UnmarkRequest extends Request{
    private String[] inputArray;
    private TasksList tasksList;
    private static final String UNMARK_MSG = "Sure! I've marked this task as not done yet: \n";

    public UnmarkRequest(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number!");
        }
         try {
             int taskNumber = Integer.parseInt(this.inputArray[1]);
             Task deletedTask = this.tasksList.markAsUndone(taskNumber);
             super.printResponse(UnmarkRequest.UNMARK_MSG + deletedTask);
             //exception due to parsing
         } catch (NumberFormatException exception) {
             throw new DukeException("Please enter a integer for task number!");
         }
    }
}



