package duke.command;

import duke.*;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui){
        taskList.printTaskList();
    }
}