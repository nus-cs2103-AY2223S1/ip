package duke.commands;

import duke.Response;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Response execute(TaskList taskList, Storage storage) {
        List<Task> tasks = taskList.getAll().stream()
                .filter(task -> task.getTitle().contains(this.keyword))
                .collect(Collectors.toList());

        if (tasks.isEmpty()) {
            String message = String.format("No tasks found for the keyword: [%s]", this.keyword);
            return new Response(message);
        } else {
            TaskList filteredTaskLists = new TaskList(tasks);
            return new ListTasksCommand().execute(filteredTaskLists, storage);
        }
    }
}
