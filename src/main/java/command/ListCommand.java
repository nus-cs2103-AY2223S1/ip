package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append("Here are your list of tasks!\n");
        output.append(tasks.toString());
        output.append(String.format("\nYou have %d tasks in the list.\n", tasks.size()));
        return output.toString();
    }
}
