package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task curr = list.getTask(num);
            list.delete(num);
            ui.deleteMessage(curr);
            storage.save(list);
        } catch (DukeException e){
            ui.showText(e.toString());
        }
    }
}
