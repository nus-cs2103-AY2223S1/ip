package duke.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getAll().stream()
                .filter(task -> task.getTitle().contains(this.keyword))
                .collect(Collectors.toList());

        if (tasks.isEmpty()) {
            ui.showMessage(String.format("No tasks found for the keyword: [%s]", this.keyword));
        } else {
            TaskList filteredTaskLists = new TaskList(tasks);
            new ListTasksCommand().execute(filteredTaskLists, ui, storage);
        }
    }
}
