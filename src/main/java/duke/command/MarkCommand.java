package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {

    private int i;

    public MarkCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (i < 0 || i >= tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! The index is invalid.");
        }
        Task curr = tasks.get(i);
        if (curr.getStatusIcon().equals(" ")) {
            curr.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + curr);
        } else {
            ui.showMessage("This task was already done.");
            ui.showMessage("  " + curr);
        }

//        try {
//            String[] wordArr = cmd.split(" ");
//            if (wordArr.length < 2) {
//                throw new duke.DukeException("☹ OOPS!!! This mark duke.command is invalid.");
//            }
//            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
//            if (index < 0 || index >= tasks.size()) {
//                throw new duke.DukeException("☹ OOPS!!! The index is invalid.");
//            }
//            duke.task.Task curr = tasks.get(index);
//            if (curr.getStatusIcon().equals(" ")) {
//                curr.markAsDone();
//                msg(INDENT + "Nice! I've marked this duke.task as done:\n" + INDENT + "  " + curr + "\n");
//            } else {
//                msg(INDENT + "This duke.task was already done.\n" + INDENT + "  " + curr + "\n");
//            }
//        } catch (duke.DukeException e) {
//            msg(INDENT + e.getMessage() + "\n");
//        }
    }
}
