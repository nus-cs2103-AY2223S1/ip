import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int numTasks;

    // Constructor to create a task list when no previous list is available
    TaskList() { 
        this.taskList = new ArrayList<Task>();
        this.numTasks = 0;
    }

    // 
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numTasks = taskList.size();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getListSize() {
        return this.numTasks;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        numTasks += 1;
    }

    public void deleteTask(int position) {
        taskList.remove(position-1);
        numTasks -= 1;
    }

    public void printList() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(Integer.toString(i+1) + "." + taskList.get(i).toString());
        }
    }
}
