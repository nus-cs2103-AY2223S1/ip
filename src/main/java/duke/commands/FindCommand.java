package duke.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Finds and lists all tasks in the list whose description contains any of the
 * argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {
    public static final String FIND_COMMAND = "find";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(List tasks, Ui ui, Storage storage) {
        List tasksFound = getTasksWithDescriptionContainingAnyKeyword(keywords, tasks);
        ui.showToUserAsIndexedList(tasksFound.getTaskList());
    }

    /**
     * Retrieves all tasks in the list which description contain some of the
     * specified keywords.
     *
     * @param keywords for searching
     * @return list of tasks found
     */
    private List getTasksWithDescriptionContainingAnyKeyword(Set<String> keywords, List tasks) {
        final List matchedTasks = new List();
        for (Task task : tasks.getTaskList()) {
            java.util.List<String> descriptionWords = Arrays.asList(task.getDescription().split("\\s+"));
            final Set<String> wordsInDescription = new HashSet<>(descriptionWords);
            if (!Collections.disjoint(wordsInDescription, keywords)) {
                matchedTasks.addTask(task);
            }
        }
        return matchedTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
