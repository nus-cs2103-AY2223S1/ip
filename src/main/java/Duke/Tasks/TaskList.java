package Duke.Tasks;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> tasks = new ArrayList<>();



    public void addTask(Task task){
        this.tasks.add(task);
    }

    public String showTasks(){
        Task curTask;
        String output = "";
        for(int i = 0; i < tasks.size(); i++) {
            curTask = tasks.get(i);
            output += (i + 1) + "." + curTask.toString() + "\n";
        }
        return output;
    }

    public int getTotalTaskNumber() { return this.tasks.size(); }

    public Task markAsDone(int index) throws IndexOutOfBoundsException {
        Task doneTask = this.tasks.get(index);
        doneTask.setIsDone(true);
        return doneTask;
    }

    public Task deteleTask(int index) throws IndexOutOfBoundsException {
        return this.tasks.remove(index - 1);
    }
    

    public Task getTaskByIndex(int index) { return this.tasks.get(index); }

    @Override
    public String toString() { return this.tasks.toString();}
}
