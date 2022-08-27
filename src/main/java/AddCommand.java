public class AddCommand extends Command {

    private char taskType;
    String userInput;

    public AddCommand(char taskType, String userInput) {
        this.taskType = taskType;
        this.userInput = userInput;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskType == 'T') {
            tasks.addTodo(userInput);
        } else if (taskType == 'D') {
            tasks.addDeadline(userInput);
        } else if (taskType == 'E') {
            tasks.addEvent(userInput);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
