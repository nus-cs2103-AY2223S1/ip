package duke;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private Ui ui;

    public TaskList(List<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public List<Task> getTaskList(){
        return taskList;
    }

    public void printTaskList(){
        for(int i = 0; i<taskList.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + taskList.get(i));
        }
    }
    public int size(){
        return taskList.size();
    }

    /**
     * Marks a task as done.
     * @param index The 1-based index of the task to be marked
     */
    public void markDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setDone();
    }

    /**
     * Marks a task as not done.
     * @param index The 1-based index of the task to be un-marked
     */
    public void markNotDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setNotDone();
    }

    /**
     * Deletes a task.
     * @param index The 0-based index of the task to be deleted
     */
    public void delete(Integer index) throws DukeException{
        Task task = getTask(index);
        taskList.remove(task);
    }

    public void addTask(Task task) throws DukeException{
        taskList.add(task);
    }

    /**
     * A helper function retrieved a task from the Task List based on index, and throws
     * an exception if the index is invalid.
     * @param index The 1-based index of the task.
     * @return The retrieved task.
     */
    public Task getTask(Integer index) throws DukeException{
        if(index < 1 || index > taskList.size()){
            throw new DukeException("☹ OOPS!!! That index is out of bounds.");
        }
        return taskList.get(index-1);
    }




}
