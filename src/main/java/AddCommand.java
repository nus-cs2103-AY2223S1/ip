import java.io.IOException;

public class AddCommand extends Command {

    private Task t;

    public AddCommand(Task t) {
        super();
        this.t = t;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        try {
            storage.appendToFile(t.toFile());
            tasks.addTask(t);
            StringBuilder addMessage = new StringBuilder();
            addMessage.append("\tGot it. I've added this task:\n");
            addMessage.append("\t\t" + t.toString() + "\n");
            addMessage.append(String.format("\tNow you have %d tasks in the list.", tasks.nTasks()));
            ui.print(addMessage);
        } catch (IOException e) {
            throw new PlutoException("\tOOPS!!! Couldn't add task. Try again!");
        }
    }

}
