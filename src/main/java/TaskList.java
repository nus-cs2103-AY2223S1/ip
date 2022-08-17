import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> taskList=new ArrayList<>();
    //the method that add task to list and print reply.
    public void addTask(Task task){
        System.out.println("added: "+task.printTask());
        this.taskList.add(task);
    }
    //the method that return all the tasks in a formatted string
    public String listAllTask(){
        String res="";
        for(int i=0;i<this.taskList.size();i++){
            if(this.taskList.get(i)!=null){
                res+=(i+1)+". "+this.taskList.get(i).printTask()+"\n";
            }
        }
        return res;
    }
    //mark a certain task as done and print reply
    public void markAsDone(int i){
        this.taskList.get(i).taskDone();
        System.out.println("Nice! I've marked this task as done:\n"+this.taskList.get(i).printTask());
    }
    //mark a certain task as not done and print reply
    public void markUndone(int i){
        this.taskList.get(i).taskUndone();
        System.out.println("OK, I've marked this task as not done yet:\n"+this.taskList.get(i).printTask());
    }
}
