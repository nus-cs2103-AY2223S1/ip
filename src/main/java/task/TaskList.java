package task;

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

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, this.tasks.get(i));
        }
        return "Here are the tasks in your list\n" + tasksString;
    }

    public List<String> toStorageRepresentation() {
        return this.tasks.stream()
                .map(Task::toStorageRepresentation)
                .collect(Collectors.toList());
    }
}
