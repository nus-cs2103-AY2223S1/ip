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

    public Boolean isEmpty() {
        return this.size() > 0;
    }

    public int getSize() {
        return this.size;
    }

    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    public void delete(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        this.tasks.remove(pos-1);
    }

    public Task get(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        return this.tasks.get(pos-1);
    }

    public void mark(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        this.tasks.get(pos-1).mark();
    }

    public void unmark(int pos) throws TaskListOutOfBoundsException{
        if (pos > this.getSize()) {
            throw new TaskListOutOfBoundsException(String.format("%s%d", this.ERRORMESSAGE,this.getSize()));
        }
        this.tasks.get(pos-1).unmark();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

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