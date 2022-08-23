public class AddTaskCommand extends Command {
    private TaskType taskType;
    private String userInput;

    public AddTaskCommand(String userInput) {
        this.userInput = userInput;
        if (userInput.matches("(?i)^(todo)(.*)")) {
            this.taskType = TaskType.TODO;
        } else if (userInput.matches("(?i)^(deadline)(.*)")) {
            this.taskType = TaskType.DEADLINE;
        } else if (userInput.matches("(?i)^(event)(.*)")) {
            this.taskType = TaskType.EVENT;
        }
    }

    /**
     * Stores the specified task (to-do, event, deadline) into the linked list,
     * provided the respective task formats are properly followed.
     *
     * @param tasks The task list to add the task to
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Task addedTask = this.taskType.validateCommand(this.userInput);
        Ui.prettyPrint(tasks.addTask(addedTask));
    }
}
