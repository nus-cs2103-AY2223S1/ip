package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command{
    
    private final int taskIndex;
    private final String action;
    
    public MarkCommand(String action, int taskIndex) {
        this.taskIndex = taskIndex;
        this.action = action;
    }
    
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try { 
            tasks.mark(action, taskIndex);
            storage.store(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index not found");
        }

    }
}
