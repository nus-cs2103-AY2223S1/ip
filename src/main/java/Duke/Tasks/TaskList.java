package Duke.Tasks;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> taskList;

    public boolean checkTaskContent(String content) throws EmptyContentException


    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public String showTasks(){
        Task cur;
        String output = "";
        for(int i = 0; i < taskList.size(); i++) {
            cur = taskList.get(i);
            output += cur.toString() + "\n";
        }
        return output;
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }




    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
