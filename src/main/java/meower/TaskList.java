package meower;

import java.util.ArrayList;

import exception.TaskListOutOfBoundsException;
import task.Task;

public class TaskList {

    private final String MESSAGE_ERROR_OUTOFBOUNDS = "Positional argument out of bounds for TaskList of size ";

    private ArrayList<Task> tasks;
    private TaskList searchTasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.size = 0;
    }

    public TaskList(TaskList taskList) {
        this.tasks = new ArrayList<Task>();
        this.tasks.addAll(taskList.tasks); //deep copy TaskList
        this.size = taskList.getSize();
    }

    
    /** 
     * Returns true if tasklist is empty, false otherwise
     * @return Boolean
     */
    public Boolean isEmpty() {
        return !(this.size > 0);
    }

    
    /** 
     * Returns number of tasks in the tasklist
     * @return int
     */
    public int getSize() {
        return this.size;
    }

    
    /** 
     * adds a task into the back of the tasklist
     * @param newTask task inputted by user to be added to tasklist
     */
    public void add(Task newTask) {
        assert newTask.isEmpty() == false: "Tasks added to tasklist should never be empty";
        this.tasks.add(newTask);
        this.size += 1;
    }

    
    /** 
     * deletes the task at the specified index in the tasklist, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos user inputted index to delete task
     * @throws TaskListOutOfBoundsException thrown when user inputted pos is outside the size of the tasklist
     */
    public void delete(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.MESSAGE_ERROR_OUTOFBOUNDS,this.getSize()));
        }
        this.tasks.remove(pos-1);
        this.size -= 1;
    }

    
    /** 
     * returns the task at the specified index in the tasklist, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos user inputted index to get task
     * @return Task
     * @throws TaskListOutOfBoundsException thrown when user inputted pos is outside the size of the tasklist
     */
    public Task get(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.MESSAGE_ERROR_OUTOFBOUNDS,this.getSize()));
        }
        return this.tasks.get(pos-1);
    }

    
    /** 
     * marks the task at the specified index in the tasklist as done, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos user inputted index to mark task done
     * @throws TaskListOutOfBoundsException thrown when user inputted pos is outside the size of the tasklist
     */
    public void mark(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.MESSAGE_ERROR_OUTOFBOUNDS,this.getSize()));
        }
        this.tasks.get(pos-1).mark();
    }

    
    /** 
     * marks the task at the specified index in the tasklist as not done, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos user inputted index to mark task not done
     * @throws TaskListOutOfBoundsException thrown when user inputted pos is outside the size of the tasklist
     */
    public void unmark(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.MESSAGE_ERROR_OUTOFBOUNDS,this.getSize()));
        }
        this.tasks.get(pos-1).unmark();
    }

    
    /** 
     * returns the ArrayList of tasks
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    
    
    /** 
     * returns a list of all tasks that have the user specified keyword
     * @param keyword user inputted string by which to filter the tasks
     * @return TaskList
     */
    public TaskList search(String keyword) {
        this.searchTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                this.searchTasks.add(task);
            }
        }
        return this.searchTasks;
    }

    
    /** 
     * replaces the current tasks in the tasklist with the tasks in the user inputted tasklist
     * @param newList user inputted tasklist
     */
    public void replace(TaskList newList) {
        this.tasks = new ArrayList<Task>();
        this.tasks.addAll(newList.tasks);
    }

    /** 
     * Returns the list command format of the tasks in index order
     * @return String
     */
    @Override
    public String toString() {
        String output = "Tasks: \n";
        int count = 1;
        if (tasks.size() == 0) {
            return "No tasks! Yay!";
        }
        for (Task task : tasks) {
            output += String.valueOf(count) + ". " + task + "\n";
            count += 1;
        }
        output = output.substring(0, output.length()-1);
        return output;
    }
}