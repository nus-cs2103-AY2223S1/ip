package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    /**
     * Marks a task as not done.
     * @param index The 1-based index of the task to be un-marked
     */
    public void markNotDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task.toString());
    }

    /**
     * Deletes a task.
     * @param index The 1-based index of the task to be deleted
     */
    public void delete(Integer index) throws DukeException{
        Task task = getTask(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + taskList.get(index-1).toString() +"\nNow you have " + (taskList.size() - 1) +" tasks in the list.");
        taskList.remove(task);
    }

    public void addTask(Task task) throws DukeException{
        taskList.add(task);
    }

    public void getItemsOnDate(String stringDate[]) throws DukeException {
        LocalDate date = getDate(stringDate[0]);
        List<String> onThisDate = new ArrayList<>();
        List<String> byThisDate = new ArrayList<>();
        for (Task task : taskList) {
            if(task instanceof Event){
                Event event = (Event) task;
                if(event.onThisDate(date)){
                    onThisDate.add(event.toStringDate());
                }
            }
            if(task instanceof Deadline ){
                Deadline deadline = (Deadline) task;
                if( deadline.byThisDate(date)){
                    byThisDate.add( deadline.toStringDate());
                }
            }
        }
        System.out.println("Things on this day :" );
        for(String s : onThisDate) {
            System.out.println(s + "\n");
        }
        System.out.println("Things to do by this day :" );
        for(String s : byThisDate) {
            System.out.println(s + "\n");
        }
    }

    /**
     * A helper function retrieved a task from the Task List based on index, and throws
     * an exception if the index is invalid.
     * @param index The 1-based index of the task.
     * @return The retrieved task.
     */
    private Task getTask(Integer index) throws DukeException{
        if(index < 1 || index > taskList.size()){
            throw new DukeException("☹ OOPS!!! That index is out of bounds.");
        }
        return taskList.get(index-1);
    }

    private static LocalDate getDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }


}
