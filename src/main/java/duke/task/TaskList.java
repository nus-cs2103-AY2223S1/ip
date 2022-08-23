package duke.task;

import duke.exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public void add(Task task) {
        this.tasks.add(task);
    }
    
    public Task delete(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex);
        return task;
    }
    
    public Task mark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    public Task unmark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Filters the list of <code>Task</code> objects in the <code>TaskList</code>, keep <code>Task</code> objects with
     * descriptions matching the search term. Returns the list of found <code>Task</code> objects as a new 
     * <code>TaskList</code>.
     * 
     * @param searchTerm Search term used to find the list of matching <code>Tasks</code>.
     * @return <code>TaskList</code> containing the list of found <code>Task</code> objects.
     */
    public TaskList find(String searchTerm) {
        List<Task> foundTasksList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasksList.add(task);
            }
        }
        return new TaskList(foundTasksList);
    }
    
    public int size() {
        return this.tasks.size();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public void listTasks() {
        int len = this.tasks.size();
        if (len == 0) {
            System.out.println("       YOU HAVE NO TASKS");
        } else {
            for (int i = 0; i < len; i++) {
                Task task = this.tasks.get(i);
                System.out.printf("   %d.%s%n", i + 1, task);
            }
        }
    }
    
    public boolean isValidIndex(int index) {
        return index >= 1 && index <= this.size();
    }
}
