package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }
    
    public Task get(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public ArrayList<Task> filter(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
    
    public int size() {
        return taskList.size();
    }
    
    //to save
    public void add(Task t) {
        taskList.add(t);
    }
    
    public void delete(int index) {
        taskList.remove(index);
    }
    
    public void mark(int index) {
        taskList.get(index).mark();
    }
    
    public void unmark(int index) {
        taskList.get(index).unmark();
    }
    
}
