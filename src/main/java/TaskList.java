import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<String> taskList=new ArrayList<>();
    //the method that add task to list and print reply.
    public void addTask(String task){
        System.out.println("added: "+task);
        this.taskList.add(task);
    }
    //the method that return all the tasks in a formatted string
    public String listAllTask(){
        String res="";
        for(int i=0;i<this.taskList.size();i++){
            if(this.taskList.get(i)!=null){
                res+=(i+1)+". "+this.taskList.get(i)+"\n";
            }
        }
        return res;
    }
}
