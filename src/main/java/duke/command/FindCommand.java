package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to find task with names that contain the keyword.
 */
public class FindCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private String keyword;
    private TaskList ansTaskList;
    /**
     * Constructor of FindCommand
     *
     * @param info Type of command
     * @param keyword Keyword to find
     */
    public FindCommand(String info, String keyword) {
        super(info);
        this.keyword = keyword;
        ansTaskList = new TaskList();
    }
    /**
     * Execute find command
     *
     * @param ui Ui to show find messages.
     * @param taskList TaskList to find tasks with name that has keywords in it.
     */
    public void execute(Ui ui, TaskList taskList) {
        TaskList ansTaskList = new TaskList();
        int cnt = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getName().contains(keyword)) {
                if (cnt == 1) {
                    ui.showFindMsg();
                }
                ansTaskList.add(task);
                ui.showTask(cnt, task);
                cnt++;
            }
        }

        if (cnt == 1) {
            ui.showFindEmptyMsg();
        }
    }
}
