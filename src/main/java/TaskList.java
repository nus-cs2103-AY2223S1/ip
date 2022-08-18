import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public ArrayList<? super Task> addTasks(Task task) {
        tasks.add(task);
        return tasks;
    }

    public void list() {
        if (tasks.size() <= 0) {
            System.out.println("There are no tasks in the list!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + tasks.get(i).toString());
        }
    }

    public String markTask(Integer n) {
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.done();
        return taskMark.toString();
    }

    public String unmarkTask(Integer n) {
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.notDone();
        return taskMark.toString();
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
