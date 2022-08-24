package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void removeTask(Task remove) {
        this.taskList.remove(remove);
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public TaskList matchingItems(String input) {
        TaskList matchingList = new TaskList();
        Task[] tasks = new Task[taskList.size()];
        String[] descriptions = new String[taskList.size()];
        for (int i = 0; i < this.getLength(); i++){
            tasks[i] = this.getTaskAt(i);
        }
        for (int i = 0; i < this.getLength(); i++){
            descriptions[i] = tasks[i].getDescription();
        }
        for (int i = 0; i < this.getLength(); i++){
            String desc = descriptions[i];
            if (desc.contains(input)) {
                matchingList.addTask(tasks[i]);
            }
        }
        return matchingList;
    }

    public int getLength() {
        return taskList.size();
    }

    public Task getTaskAt(int index) {
        return taskList.get(index);
    }
}
