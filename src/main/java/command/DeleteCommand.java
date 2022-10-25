package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private final String STRING;

    public DeleteCommand(String str) {
        this.STRING = str;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(STRING.substring(6).trim());
            if (index <= tasks.size() && index > 0) {
                StringBuilder output = new StringBuilder();
                output.append("Noted. I've removed this task:\n");
                output.append(tasks.removeTask(index - 1).toString());
                storage.saveLocalData(tasks.getTasks());
                output.append(String.format("\nNow you have %d tasks in the list.\n", tasks.size()));
                return output.toString();
            } else {
                throw new DukeException("Index invalid, no such task exists.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Index invalid, please enter an integer.");
        }
    }
}
