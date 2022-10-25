package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class MarkCommand extends Command {

    private final String STRING;

    public MarkCommand(String str) {
        this.STRING = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(STRING.substring(4).trim());
            if (index <= tasks.size() && index > 0) {
                StringBuilder output = new StringBuilder();
                Task task = tasks.getTasks().get(index - 1);
                if (!task.isDone()) {
                    task.toggleDoneness();
                    storage.saveLocalData(tasks.getTasks());
                    output.append("Good job for doing this task!\n");
                    output.append(task);
                } else {
                    output.append("This task has already been marked done.\n");
                    output.append(task);
                }
                return output.toString();
            } else {
                throw new DukeException("Index invalid, no such task exists.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Index invalid, please enter an integer.");
        }
    }
}
