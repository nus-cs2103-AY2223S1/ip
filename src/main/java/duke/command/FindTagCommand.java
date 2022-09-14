package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import java.util.function.Predicate;

public class FindTagCommand extends Command {

    /**
     * A constructor that encapsulates the information of finding tasks with a tag.
     *
     * @param parameters input of the tag for which we want to search.
     */
    public FindTagCommand(String parameters) {
        super((tasks, ui, storage) -> {
            Predicate<? super Task> doesTaskContainTag = t -> t.containsTag(parameters);
            TaskList filteredTasks = tasks.filterTasksWithThisPredicate(doesTaskContainTag);
            ui.listFilteredTasks(tasks, filteredTasks);
        });
    }
}
