import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    TaskManager(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void remove(int index){
        tasks.remove(index);
    }

    public int getSize(){
        return tasks.size();
    }
    public void printAllOut(){
        for(int i = 0; i < tasks.size(); i++){
            int index = i + 1;
            System.out.println(index + ". " + tasks.get(i).toString());
        }
    }
}
