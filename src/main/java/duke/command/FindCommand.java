package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private String keyword;
    private TaskList ansTaskList;
    public FindCommand(String info, String keyword) {
        super(info);
        this.keyword = keyword;
        ansTaskList = new TaskList();
    }

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
