import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> taskArray;
    private int count = 0;

    public Tasklist(){
        this.taskArray = new ArrayList<>();
    }

    public void add(Task task){
        this.taskArray.add(task);
        this.count += 1;
    }

    public Task getTask(int position) throws IndexOutOfBoundsException{
        return taskArray.get(position - 1);
    }

    public int getCount(){
        return this.count;
    }

    public boolean markTaskAtPos(int position){
        try {
            Task currTask = getTask(position);
            currTask.markAsDone();
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean unmarkTaskAtPos(int position){
        try {
            Task currTask = getTask(position);
            currTask.unmark();
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Task deleteTaskAtPos(int position) throws IndexOutOfBoundsException {
        Task deletedTask = getTask(position);
        this.taskArray.remove(position - 1);
        this.count -= 1;
        return deletedTask;
    }

    @Override
    public String toString(){
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            stringedList += (i + 1) + ". " + getTask(i + 1).toString() + "\n";
        }
        return "Here are the tasks in your list:\n" + stringedList;
    }
}
