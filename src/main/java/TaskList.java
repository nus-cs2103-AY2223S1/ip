import java.io.Serializable;
import java.util.LinkedList;

public class TaskList implements Serializable {

    // Use a linked list to store the user's tasks
    private LinkedList<Task> tasks;


    // Constructor
    public TaskList() {
        this.tasks = new LinkedList<>();
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


    @Override
    public String toString() {
        return String.format("%s", this.tasks.toString());
    }
    
}
