package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.function.Predicate;

public class FindCommand extends Command {

    /**
     * A constructor that encapsulates the information of finding a particular task.
     */
    public FindCommand(String parameters) {
        super((tasks, ui, storage) -> {
            Predicate<? super Task> doesTaskNameContain = t -> t.getDescription().contains(parameters);
            TaskList filteredTasks = tasks.filterTasksWithThisPredicate(doesTaskNameContain);
            ui.listFilteredTasks(tasks, filteredTasks);
        });
    }
}
