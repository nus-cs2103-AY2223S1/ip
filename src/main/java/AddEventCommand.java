import java.util.Arrays;

public class AddEventCommand extends Command {
    private int limit;
    private String taskDescription;
    private String at;

    public AddEventCommand(String[] inputs) {
        super(CommandType.EVENT);
        limit = findElem(inputs, "/at");
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        at = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (limit == -1) {
            ui.printEventErrorMessage();
            return;
        }
        tasks.addEvent(taskDescription, at);
        ui.printTaskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}

