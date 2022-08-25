package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to find task with names that contain the keyword.
 */
public class FindCommand extends Command {
    
    private String keyword;
    
    /**
     * Constructor of FindCommand
     *
     * @param info Type of command
     * @param keyword Keyword to find
     */
    public FindCommand(String info, String keyword) {
        super(info);
        this.keyword = keyword;
    }
    
    /**
     * Execute find command
     *
     * @param ui Ui to show find messages.
     * @param taskList TaskList to find tasks with name that has keywords in it.
     */
    public void execute(Ui ui, TaskList taskList) {
        TaskList ansTaskList = new TaskList();
        int count = 1;

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getName().contains(keyword)) {
                if (count == 1) {
                    ui.showFindMessage();
                }
                ansTaskList.add(task);
                ui.showTask(count, task);
                count++;
            }
        }

        if (count == 1) {
            ui.showFindEmptyMessage();
        }
    }
}
