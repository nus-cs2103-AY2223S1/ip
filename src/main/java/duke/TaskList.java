package duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskArray;

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    public void add(Task task) {
        taskArray.add(task);
    }

    public List<Integer> findTasks(String description) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < taskArray.size(); i++) {
            String taskDescription = taskArray.get(i).getDescription();
            if (taskDescription.contains(description)) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public void delete(int index) {
        taskArray.remove(index);
    }

    public Task getTask(int index) {
        return taskArray.get(index);
    }

    public int getSize() {
        return taskArray.size();
    }
}
