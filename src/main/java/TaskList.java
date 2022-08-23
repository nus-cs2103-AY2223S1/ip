import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private TaskMessager taskMessager;

    public TaskList() {
        tasks = new ArrayList<>();
        taskMessager = new TaskMessager();
    }

    public void addTask(String strTask) {
        Task task = new Task(strTask);
        tasks.add(task);
        taskMessager.addMessage(strTask);
    }

    public void listTask() {
        taskMessager.listMessage(toString());
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.mark();
        taskMessager.markMessage(task);
    }

    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.unmark();
        taskMessager.unmarkMessage(task);
    }

    @Override
    public String toString() {
        int size = tasks.size();
        String text = String.format("%d. %s", 1, tasks.get(0));
        for (int i = 1; i < size; i++) {
            text += String.format("\n%d. %s", i+1, tasks.get(i));
        }
        return text;
    }
}
