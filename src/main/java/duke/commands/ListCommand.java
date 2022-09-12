package duke.commands;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListCommand extends Command {
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    /**
     * Returns a list of tasks that is currently stored in the task list.
     *
     * @param taskList Task list that contains all the tasks.
     */
    public String execute(TaskList taskList) {
        List<String> tasks = new ArrayList<>();
        tasks.add("Here are the tasks in your list:");
        ListIterator<Task> listIterator = taskList.getListIterator();
        while (listIterator.hasNext()) {
            int taskIndex = listIterator.nextIndex() + DISPLAYED_INDEX_OFFSET;
            Task task = listIterator.next();
            tasks.add(taskIndex + "." + task);
        }
        return String.join("\n", tasks);
    }
}
