import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> arrayL;

    public TaskList() {
        arrayL = new ArrayList<Task>();
    }
    public void addTask(Task t) {
        this.arrayL.add(t);
    }
    public Task getTask(int i) {
        return arrayL.get(i);
    }
    public void delete(int in) {
        System.out.println("ok, I've deleted this take from array");
        System.out.println(arrayL.get(in-1).toString());
        arrayL.remove(in - 1);
    }
}
