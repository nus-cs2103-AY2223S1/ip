package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(Task... taskList) {
        this.taskList = new ArrayList<>(Arrays.asList(taskList));
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public String[] toStringList() {
        return this.taskList.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    public int getLength() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns a TaskList consisting only of Tasks that satisfy the given predicate.
     * @param pred a predicate to apply to each Task to determine if it should be included
     * @return the new TaskList
     */
    public TaskList filter(Predicate<? super Task> pred) {
        return new TaskList(this.taskList.stream().filter(pred).toArray(Task[]::new));
    }
}
