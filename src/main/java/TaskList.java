import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> taskList=new ArrayList<>();
    //the method that add task to list and print reply.
    public int countTask(){
        int res=0;
        for (int i=0;i<taskList.size();i++){
            if(taskList.get(i)!=null){
                res+=1;
            }
        }
        return res;
    }
    //delete the task and print out reply
    public void delete(int i, Storage storage) throws DukeException{
        try {
            int index = i - 1;
            Task deletedTask = this.taskList.remove(i - 1);
            System.out.println(" Noted. I've removed this task:\n" + deletedTask.printTask() +
                    "\n" + "Now you have " + this.countTask() + " tasks in the list.");
            storage.updateFile(this.taskList);
        }catch (IndexOutOfBoundsException e){
            throw new DukeException("Sorry, the command is not in right format.");
        }
    }
    public void addTask(Task task){
        this.taskList.add(task);
        System.out.println("Got it. I've added this task:\n"+task.printTask()+
                "\nNow you have "+this.countTask()+" task in the list.");
    }
    //the method that return all the tasks in a formatted string
    public String listAllTask(){
        String res="";
        for(int i=0;i<this.taskList.size();i++){
            if(this.taskList.get(i)!=null){
                res+=(i+1)+". "+this.taskList.get(i).printTask()+"\n";
            }
        }
        return  "Here are the tasks in your list:\n"+res;
    }
    //mark a certain task as done and print reply
    public void markAsDone(int i, Storage storage) throws DukeException{
        this.taskList.get(i).taskDone();
        System.out.println("Nice! I've marked this task as done:\n"
                +this.taskList.get(i).printTask());
        storage.updateFile(this.taskList);
    }
    //mark a certain task as not done and print reply
    public void markUndone(int i, Storage storage) throws DukeException{
        this.taskList.get(i).taskUndone();
        System.out.println("OK, I've marked this task as not done yet:\n"
                +this.taskList.get(i).printTask());
        storage.updateFile(this.taskList);
    }

    public List<Task> getTaskList(){
        return this.taskList;
    }
    public String getASpecificDay(String s){
        try {
            String day = s.split(" ")[1];
            LocalDate d = LocalDate.parse(day);
            String res="";
            for (int i = 0; i < this.taskList.size(); i++){
                if (this.taskList.get(i).getDay() != null){
                    if (this.taskList.get(i).getDay().equals(d)){
                        res += this.taskList.get(i).printTask() + "\n";
                    }
                }
            }
            if (res.equals("")){
                return "You don't have tasks on this day.";
            }else {
                return res;
            }
        }catch (Exception e){
            return "the input format is not correct " + e.getMessage();
        }
    }
}
