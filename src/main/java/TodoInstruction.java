import java.util.Map;

public class TodoInstruction extends Instruction {
    public TodoInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showMessages("Sorry, I will need a description for the to-do.");
            return;
        }
        Todo todo = new Todo(this.getMainArgument());
        taskList.addTask(todo);
        ui.showMessages("Got it, I've added this to-do:", todo.toString());
    }
}
