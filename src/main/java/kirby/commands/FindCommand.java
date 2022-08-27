package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.Ui;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String inputString;

    public FindCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("find");
        }
        String keyword = inputString.split(" ")[1];
        ArrayList<Task> res = tasks.findTask(keyword);
        for (Task task: res) {
            System.out.println(task.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
