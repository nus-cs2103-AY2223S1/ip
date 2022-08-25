import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private List<Task> tasks =  new ArrayList<Task>();;
    private int size;

    public TaskList(){
        size = 0;
    }

    public void addTask(Task element){
        tasks.add(element);
        size++;
    }

    public void addTaskByIdx(int idx, Task element){
        tasks.add(idx, element);
        size++;
    }

    public void removeTask(Task task){
        tasks.remove((Task)task);
        size--;
    }

    public Task getTask(int idx){
        return tasks.get(idx);
    }

    public List<Task> getTaskList(){
        return tasks;
    }
    public int getSize(){
        return size;
    }

    @Override
    public String toString(){
        return Arrays.toString(tasks.toArray());
    }

}
