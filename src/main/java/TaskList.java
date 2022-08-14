import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void printNumTasks() {
        System.out.println("You have " + tasks.size() + " task" + (tasks.size() != 1 ? "s" : "") + " in the list.");
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added:\n\t" + task);
        printNumTasks();
    }

    public void deleteTask(int taskNum) {
        Task task = getTask(taskNum);
        if (task != null) {
            tasks.remove(taskNum - 1);
            System.out.println("Task removed:\n\t" + task);
            printNumTasks();
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty!");
        } else {
            System.out.println("Task list:");
            for (int i = 0; i < tasks.size(); i++) {
                int taskNum = i + 1;
                Task task = tasks.get(i);
                System.out.println(taskNum + "." + task);
            }
        }
    }

    public Task getTask(int taskNum) {
        if (taskNum <= tasks.size()) {
            return tasks.get(taskNum - 1);
        } else {
            System.out.println("No such task!");
            return null;
        }
    }
}
