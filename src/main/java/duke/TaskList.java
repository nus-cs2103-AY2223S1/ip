package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    public String taskSaveToString(int index) {
        return this.tasks.get(index).storedString();
    }

    public void markAsDone(int index) {
        this.tasks.get(index).markDone();
    }

    public void unMarkDone(int index) {
        this.tasks.get(index).unMark();
    }

    /**
     * Find all the tasks with given keyword in TaskList.
     * @param keyword Keyword to find tasks.
     * @return TaskList containing matching tasks.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).hasKeyword(keyword)) {
                tasksWithKeyword.add(this.tasks.get(i));
            }
        }
        return new TaskList(tasksWithKeyword);
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
