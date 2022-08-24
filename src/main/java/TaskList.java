import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public Task getTask(int taskNum) {
        return listOfTasks.get(taskNum);
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public int getTaskSize() {
        return this.listOfTasks.size();
    }

    public void markTask(int TaskNum) {
        try {
            Task currTask = listOfTasks.get(TaskNum);
            currTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The task you are trying to mark does not exist");
        }
    }

    public void unmarkTask(int TaskNum) {
        try {
            Task currTask = listOfTasks.get(TaskNum);
            currTask.markAsNotDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The task you are trying to mark as not done does not exist");
        }
    }

    public void deleteTask(int TaskNum) {
        try {
            listOfTasks.remove(TaskNum);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The task you are trying to delete does not exist");
        }
    }
}
