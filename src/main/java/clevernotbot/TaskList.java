package clevernotbot;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
        size = tasks.size();
    }

    public void addTask(Task element){
        tasks.add(element);
        size++;
    }

    public void addTaskByIdx(int idx, Task element){
        tasks.add(idx, element);
        size++;
    }

    public void removeTask(Task task){
        tasks.remove((Task)task);
        size--;
    }

    public Task getTask(int idx){
        return tasks.get(idx);
    }

    /**
     * Returns an ArrayList of task with the search word provided.
     *
     * @param searchWord The word that you want to search in the list.
     * @return An ArrayList of task with the search word provided.
     */
    public ArrayList<Task> findTasks(String searchWord){
        ArrayList<Task> taskArr = new ArrayList<>();
        for (Task task: tasks) {
            if((task.getName().toLowerCase()).contains(searchWord.toLowerCase())) {
                taskArr.add(task);
            }
        }
        return taskArr;
    }

    public ArrayList<Task> getTaskList(){
        return tasks;
    }
    public int getSize(){
        return size;
    }

    @Override
    public String toString(){
        return Arrays.toString(tasks.toArray());
    }

}
