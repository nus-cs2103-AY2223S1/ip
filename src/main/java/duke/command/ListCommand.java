package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ListIterator;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ListIterator<Task> iterate = tasks.getIterator();
        String output = "Here are the tasks you currently have: \n";
        int qty = 0;
        while (iterate.hasNext()) {
            qty++;
            output += qty + "." + iterate.next().toString() + "\n";
        }
        ui.nextOutput(output);
    }
}
