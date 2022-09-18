package Duke.commands;


import Duke.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;


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
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ArrayList<String> text = new ArrayList<>();
            text.add(tasks.getTaskToString(taskNumber));
            return text;
        } else {
            throw new DukeException("No such task exist.");
        }
    }


}
