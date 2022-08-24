import java.util.Map;

public class DeleteInstruction extends Instruction {
    public DeleteInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (this.hasMainArgument()) {
            int taskId = Integer.parseInt(this.getMainArgument()) - 1;
            Task task = taskList.getTask(taskId);
            ui.showMessages("Ok, I've deleted this task:", task.toString());
            taskList.deleteTask(taskId);
            if (taskList.isEmpty()) {
                ui.showMessages("You have no more tasks left in your list!");
            } else {
                ui.showFormattedMessage("You have %d task(s) left in your list.%n", taskList.size());
            }
        } else {
            ui.showMessages("Sorry, you need to tell me which task to delete.");
        }
    }
}
