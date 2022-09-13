import java.io.IOException;


public abstract class TaskCommands extends Command {

    protected final int taskNumber;


    public TaskCommands(String fullInput) throws DukeException {
        String[] commands = fullInput.split(" ");
        try {
            taskNumber = Integer.parseInt(commands[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Ayo no such task number exist");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new DukeException("No such task exist.");
        }
    }


}
