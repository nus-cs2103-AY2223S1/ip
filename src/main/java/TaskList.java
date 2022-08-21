import java.util.ArrayList;

public class TaskList {
    /**
     * Class to store the list of Tasks
     */
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) { taskList.add(task); }

    public void delete(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) { return taskList.get(index); }

    public int getSize() { return taskList.size(); }

    public void printTasks() {
        int index = 0;
        Task item;
        while (index < taskList.size()) {
            item = taskList.get(index);
            System.out.println((index + 1) + "." + item.toString());
            index++;
        }
    }

    public String getTasks() {
        int index = 0;
        Task item;
        String result = "";
        while (index < taskList.size()) {
            item = taskList.get(index);
            result += (item.getInfo() + "\n");
            index++;
        }
        return result;
    }

    public void mark(int index) {
        taskList.get(index).markAsDone();
        Ui.mark();
    }

    public void unmark(int index) {
        taskList.get(index).markAsUndone();
        Ui.unmark();
    }
}
