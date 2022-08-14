/**
 * This class encapsulates a deadline command from the user.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
//    private Deadline deadline;

    DeadlineCommand(Deadline deadline) {
        super(deadline);
    }

//    /**
//     * Adds the deadline into the given task list.
//     *
//     * @param taskList The task list to add the deadline into.
//     * @return A String signalling that the deadline has been added.
//     */
//    @Override
//    public String execute(TaskList taskList) {
//        taskList.add(this.deadline);
//        return "Got it. I've added this task:\n  " + this.deadline
//                + "\nNow you have " + taskList.size() + " in the list.";
//    }
}
