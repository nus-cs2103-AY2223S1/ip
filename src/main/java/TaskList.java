import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    // creates new empty task list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    // loads existing task list from saved file
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void list() {
        this.tasks.forEach(t -> System.out.println("  " + (this.tasks.indexOf(t) + 1) + ". " + t));
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }
    
    public void markTask(int index) {
        this.tasks.get(index).mark();
    }
    
    public void unmarkTask(int index) {
        this.tasks.get(index).unmark();
    }
    
    public String toSaveFormat() {
        StringBuilder sb = new StringBuilder();
        this.tasks.forEach(t -> sb.append(t.toSaveFormat()).append("\n"));
        return sb.toString();
    }
    
    public int getSize() {
        return this.tasks.size();
    } 
}
