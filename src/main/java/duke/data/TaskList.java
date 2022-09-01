package duke.data;

import java.io.Serializable;
import java.util.LinkedList;

import duke.task.Task;

public class TaskList implements Serializable {

    // Use a linked list to store the user's tasks
    private LinkedList<Task> tasks;


    // Constructor
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    private TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }



    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int indexNumber) {
        return this.tasks.get(indexNumber);
    }

    public void setTask(int indexNumber, Task t) {
        this.tasks.set(indexNumber, t);
    }

    public void addTask(Task t) {
        this.tasks.addLast(t);
    }

    public Task removeTask(int indexNumber) {
        Task t = this.tasks.remove(indexNumber);
        return t;
    }


    /**
     * Return all Task objects which contain the specified keyword in the description.
     * 
     * @param keyword Keyword to search for in the description.
     * @return TaskList containing the Task objects.
     */
    public TaskList searchTasks(String keyword) {

        LinkedList<Task> results = new LinkedList<>();

        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                results.add(t);
            }
        }

        return new TaskList(results);
    }


    @Override
    public String toString() {
        return String.format("%s", this.tasks.toString());
    }
    
}
