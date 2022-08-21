package duke.task;

import duke.exception.InvalidIndexException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int getListSize() {
        return this.tasks.size();
    }
    public Task retrieveTask(int index) {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }

    public void markTaskDone(int index) {
        try {
            Task selectedTask = tasks.get(index);
            selectedTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }

    public ArrayList<Task> getDueTasks(LocalDate time) {
        ArrayList<Task> tmp = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.isDateEqual(time)) {
                tmp.add(task);
            }
        }
        return tmp;
    }
    public void markTaskUndone(int index) {
        try {
            Task selectedTask = tasks.get(index);
            selectedTask.markUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }
}
