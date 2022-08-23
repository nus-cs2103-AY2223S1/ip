import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<String> oldTasks;
    List<String> newTasks = new ArrayList<>();

    public TaskList(List<String> oldTasks){
        this.oldTasks = oldTasks;
    }

    public void addTask(String str){
        newTasks.add(str);
    }

    public String removeTask(int index){
        String task;
        if(index>oldTasks.size()){
            task = newTasks.remove(index-oldTasks.size()-1);
        } else{
            task = oldTasks.remove(index-1);
        }
        return task;
    }

    public int size(){
        return oldTasks.size() + newTasks.size();
    }

}
