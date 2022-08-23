package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    /**
     * Returns true if the TaskList is empty.
     *
     * @return true if the current TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks == null || this.tasks.isEmpty();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task markTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsFinished();
        return selectedTask;
    }

    public Task unmarkTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsNotFinished();
        return selectedTask;
    }

    public Task removeTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        this.tasks.remove(index);
        return selectedTask;
    }

    /**
     * Filters current TaskList to get all Tasks that matches the keyword.
     *
     * @param keyWord the keyword string.
     * @return the TaskList which contains all the matched Tasks.
     */
    public TaskList filterByKeyWord(String keyWord) {
        return new TaskList(this.tasks.stream()
                .filter(task -> task.isContainKeyWord(keyWord))
                .collect(Collectors.toList()));
    }

    /**
     * Filters current TaskList to get all Tasks that happens on the selected date.
     *
     * @param selectedDate the selected date to be used.
     * @return the TaskList which contains all the matched Tasks.
     */
    public TaskList findByDate(LocalDate selectedDate) {
        return new TaskList(this.tasks.stream()
                .filter(task -> task.isOnGivenDate(selectedDate))
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, this.tasks.get(i));
        }
        return tasksString;
    }

    public List<String> toStorageRepresentation() {
        return this.tasks.stream()
                .map(Task::toStorageRepresentation)
                .collect(Collectors.toList());
    }
}
