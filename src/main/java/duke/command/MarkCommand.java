package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command{

    private String[] arg;

    public MarkCommand(String[] arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = Integer.parseInt(arg[1]);
        if (i  <= tasks.getCount()) {
            tasks.get(i - 1).complete();
            storage.write(tasks);
            System.out.println("Nice! I have marked this task as done: ");
            System.out.println(tasks.get(i - 1));
        } else {
            System.out.println("Index does not exist");
        }
    }
}
