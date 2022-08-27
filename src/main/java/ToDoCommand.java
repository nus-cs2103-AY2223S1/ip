import java.io.IOException;

public class ToDoCommand extends Command {
    private String inputString;
    public ToDoCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.length() <= 5) {
            throw new KirbyMissingArgumentException("todo");
        }
        String taskName = inputString.substring(inputString.indexOf(' ') + 1);
        Todo todo = new Todo(taskName);
        tasks.addTask(todo);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
