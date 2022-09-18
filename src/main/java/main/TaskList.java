package main;

import java.util.ArrayList;

import exception.TaskListOutOfBoundsException;
import task.Task;

public class TaskList {

    private final String ERRORMESSAGE = "Positional argument out of bounds for TaskList of size ";

    private ArrayList<Task> tasks;
    private int size;

    TaskList() {
        this.tasks = new ArrayList<Task>();
        this.size = 0;
    }

    TaskList(TaskList taskList) {
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
     * @param newTask
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
        this.size += 1;
    }

    
    /** 
     * deletes the task at the specified index in the tasklist, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos
     * @throws TaskListOutOfBoundsException
     */
    public void delete(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        this.tasks.remove(pos-1);
        this.size -= 1;
    }

    
    /** 
     * returns the task at the specified index in the tasklist, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos
     * @return Task
     * @throws TaskListOutOfBoundsException
     */
    public Task get(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        return this.tasks.get(pos-1);
    }

    
    /** 
     * marks the task at the specified index in the tasklist as done, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos
     * @throws TaskListOutOfBoundsException
     */
    public void mark(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        this.tasks.get(pos-1).mark();
    }

    
    /** 
     * marks the task at the specified index in the tasklist as not done, throws TaskListOutOfBoundsException if index is out of bounds
     * @param pos
     * @throws TaskListOutOfBoundsException
     */
    public void unmark(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
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