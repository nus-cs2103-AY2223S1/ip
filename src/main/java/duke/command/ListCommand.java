package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getCount() == 0) {
            System.out.println("You currently have no tasks remaining! Create a task now!");
            return;
        }
        for (int i = 0; i < tasks.getCount(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            else {
                System.out.println((i+1) + ". " + tasks.get(i).toString());
            }
        }
    }


}
