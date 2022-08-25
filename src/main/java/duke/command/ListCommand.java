package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. " + tasks.getTask(i).toString() + "\n", i + 1);
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Here are the tasks in your to-do list:" ;
    }
}
