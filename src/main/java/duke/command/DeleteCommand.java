package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (i < 0 || i >= tasks.getSize()) {
            throw new DukeException("OOPS!!! The index is invalid.");
        }
        Task deleted = tasks.delete(i);
        ui.showMessage("Noted. I've removed this duke.task:");
        ui.showMessage(" " + deleted);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");

//        try {
//            String content;
//            String[] wordArr = cmd.split(" ");
//            if (wordArr.length < 2) {
//                throw new DukeException("☹ OOPS!!! This delete duke.command is invalid.");
//            }
//            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
//            if (index < 0 || index >= tasks.size()) {
//                throw new DukeException("☹ OOPS!!! The index is invalid.");
//            }
//            Task deleted = tasks.remove(index);
//            content = INDENT + "Noted. I've removed this duke.task:\n";
//            content += INDENT + "  " + deleted + "\n";
//            content += INDENT + "Now you have " + tasks.size() + " tasks in the list.\n";
//            msg(content);
//        } catch (DukeException e) {
//            msg(INDENT + e.getMessage() + "\n");
//        }
    }
}
