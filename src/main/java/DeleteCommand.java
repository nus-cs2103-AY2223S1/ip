public class DeleteCommand extends Command {
    private int taskIndex;
    private TaskList taskList;

    private DeleteCommand(String command, int taskIndex, TaskList taskList) {
        super(command);
        this.taskIndex = taskIndex;
        this.taskList = taskList;
    }

    public static DeleteCommand of(String command, TaskList taskList) throws IllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Parser.getTaskIndex(command, taskList);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a number to delete a task.\n");
        }

        try {
            taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to delete a task.\n", taskList.size())
            );
        }
        return new DeleteCommand(command, taskIndex, taskList);
    }

    @Override
    public void execute() {
        this.taskList.remove(taskIndex);
    };
}
