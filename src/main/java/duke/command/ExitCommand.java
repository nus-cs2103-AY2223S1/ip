package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command{
    
    public ExitCommand() {
        this.isExit = true;
    }
    
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        System.out.println("Goodbye...");
    }
}
