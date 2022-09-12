package duke;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.WEEKS;

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

    public List<Integer> getUpcomingTasks() {
        List<Integer> searchResults = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekLaterDate = currentDate.plus(1,WEEKS);
        Task task;
        for (int i = 0; i < taskArray.size(); i++) {
            task = taskArray.get(i);
            if (task.getTaskType() != "T") { //if task is not todo type
                LocalDate taskDate = taskArray.get(i).getDate();
                if (taskDate.isBefore(oneWeekLaterDate) && taskDate.isAfter(currentDate)) {
                    searchResults.add(i);
                }
            }
        }
        return searchResults;
    }
}
