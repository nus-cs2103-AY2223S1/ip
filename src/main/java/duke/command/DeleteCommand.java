package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = Integer.parseInt(input);
        Task removed = tasks.remove(i - 1);
        System.out.println("Noted. I have removed this task:");
        System.out.println(removed);
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
    }
}
