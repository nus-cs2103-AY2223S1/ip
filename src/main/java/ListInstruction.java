import java.util.Map;

public class ListInstruction extends Instruction {
    public ListInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isEmpty()) {
            ui.showMessages("You have no tasks at the moment!");
        } else {
            ui.showOrderedList(taskList);
        }
    }
}
