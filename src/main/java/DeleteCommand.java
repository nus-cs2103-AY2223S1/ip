public class DeleteCommand extends Command {
    private TasksList tasksList;
    private String[] inputArray;
    private static final String deleteMsg = "Noted. I've removed this task:\n";

    public DeleteCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    @Override
    public String execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number!");
        }
        try {
            int taskNumber = Integer.parseInt(this.inputArray[1]);
            Task deletedTask = this.tasksList.deleteTask(taskNumber);
            StringBuilder sb = new StringBuilder();
            sb.append(DeleteCommand.deleteMsg + deletedTask + "\n" + "Now you have ");
            if (this.tasksList.getLength() <= 1) {
                sb.append(this.tasksList.getLength() + " task in the list.\n");
            } else {
                sb.append(this.tasksList.getLength() + " tasks in the list.\n");
            }
            return sb.toString();
            //exception due to parsing
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a integer for task number!");
        }

    }



}
