package Duke.commands;


import Duke.*;
import java.io.IOException;


public abstract class TaskCommands extends Command {

    protected final int taskNumber;


    public TaskCommands(String input) throws DukeException {
        String[] commands = input.split(" ");
        try {
            taskNumber = Integer.parseInt(commands[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Ayo no such task number exist");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            String line1 = tasks.getTaskToString(taskNumber);
            return line1;
        } else {
            throw new DukeException("No such task exist.");
        }
    }


}
