import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount = 0;
    public int getTaskCount() {
        return this.taskCount;
    }
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
            taskCount = tasks.size();
        }
    }

    public void setTaskMarked(int taskNumber) {
        tasks.get(taskNumber).setCompleted();
        System.out.println("Awesome :D I've marked " + tasks.get(taskNumber).toString() + " completed!");
    }

    public void setTaskUnmarked(int taskNumber) {
        tasks.get(taskNumber).setIncomplete();
        System.out.println("Okay, I've marked " + tasks.get(taskNumber).toString() + " pending!");
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount ++;
        System.out.println("Added into your bag of fabulous tasks: " + task.toString());
        printTaskCount();
    }

    public void removeTask(int taskIndex) {
        this.tasks.remove(taskIndex);
        this.taskCount --;
        System.out.println("Removed from your bag of fabulous tasks: " + tasks.get(taskIndex - 1).toString());
        printTaskCount();

    }

    private void printTaskCount() {
        if (taskCount > 1) {
            System.out.println("Now you have " + taskCount + " tasks in the bag!");
        } else {
            System.out.println("Now you have " + taskCount + " task in the bag!");
        }
    }
}
