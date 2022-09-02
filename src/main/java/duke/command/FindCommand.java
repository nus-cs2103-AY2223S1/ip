package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ListIterator;

public class FindCommand extends Command {

    private String desc;

    public FindCommand(String desc) {
        desc = desc.replace("find ", "");
        this.desc = desc;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ListIterator<Task> iterate = tasks.getIterator();
        int qty = 0;
        String output = "Here are the matching tasks I've found:";
        while (iterate.hasNext()) {
            Task currentTask = iterate.next();
            if (currentTask.getDesc().contains(desc)) {
                qty++;
                output += qty + "." + currentTask + "\n";
            }
        }
        ui.nextOutput(output);
    }
}
