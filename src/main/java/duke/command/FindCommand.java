package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String keyword;
    private StringBuilder output;

    public FindCommand(String keyword) {
        this.keyword = keyword;
        output = new StringBuilder("Here are the matching tasks in your list:");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int len = tasks.listSize();
        int counter = 1;

        for (int i = 1; i <= len; i++) {
            if (tasks.showTask(i).contains(keyword)) {
                output.append("\n").append(counter).append(". ").append(tasks.showTask(i));
                counter += 1;
            }
        }
        ui.showMessage(output.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
