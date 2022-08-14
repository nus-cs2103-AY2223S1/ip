/**
 * This class encapsulates a to-do command from the user.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
//    private Todo todo;

    TodoCommand(Todo todo) {
        super(todo);
    }

//    /**
//     * Adds the to-do into the given task list.
//     *
//     * @param taskList The task list to add the to-do into.
//     * @return A String signalling that the to-do has been added.
//     */
//    @Override
//    public String execute(TaskList taskList) {
//        taskList.add(this.todo);
//        return "Got it. I've added this task:\n  " + this.todo
//                + "\nNow you have " + taskList.size() + " in the list.";
//    }
}
