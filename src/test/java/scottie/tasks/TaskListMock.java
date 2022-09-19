package scottie.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A mock version of a TaskList for testing other classes.
 */
public class TaskListMock implements TaskList {
    public static final String MATCH_FIRST_TASK_SEARCH_TEXT = "first task search text";
    public static final String MATCH_NO_TASK_SEARCH_TEXT = "no tasks search text";

    private final List<Task> tasks = new ArrayList<>();
    private int markedTaskIndex = -1;
    private String sortOrder = "";

    @Override
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public int size() {
        return this.tasks.size();
    }

    @Override
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    @Override
    public boolean isMarked(int index) {
        return index == markedTaskIndex;
    }

    @Override
    public void markTask(int index) {
        this.markedTaskIndex = index;
    }

    @Override
    public void unmarkTask(int index) {
        this.markedTaskIndex = -1;
    }

    @Override
    public List<Task> filterTasks(String searchText) {
        if (searchText.equals(MATCH_FIRST_TASK_SEARCH_TEXT)) {
            return List.of(this.tasks.get(0));
        }
        if (searchText.equals(MATCH_NO_TASK_SEARCH_TEXT)) {
            return List.of();
        }
        return null;
    }

    @Override
    public void sortByDescription(boolean isReversed) {
        this.sortOrder = "description" + (isReversed ? " reversed" : "");
    }

    @Override
    public void sortByDate(boolean isReversed) {
        this.sortOrder = "date" + (isReversed ? " reversed" : "");
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
