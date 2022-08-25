package Duke;

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

    public int oldTasksSize(){
        return oldTasks.size();
    }

    public List<String> findMatches(String str){
        List<String> lst = new ArrayList<>();
        for(int i=0;i<oldTasks.size();i++){
            String task = oldTasks.get(i);
            if(task.contains(str)){
                lst.add(task);
            }
        }
        for(int i=0;i<newTasks.size();i++){
            String task = newTasks.get(i);
            if(task.contains(str)){
                lst.add(task);
            }
        }
        return lst;
    }

}
