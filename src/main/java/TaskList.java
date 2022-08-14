public class TaskList {
    protected Task[] tasks;
    protected int numTasks;

    public TaskList() {
        this.tasks = new Task[100];
        this.numTasks = 0;
    }

    public void addTask(Task task) {
        tasks[numTasks++] = task;
        System.out.println("Task added:\n" + task);
    }

    public void displayTasks() {
        if (numTasks == 0) {
            System.out.println("Your task list is empty!");
        } else {
            System.out.println("Task list:");
            for (int i = 0; i < numTasks; i++) {
                int taskNum = i + 1;
                Task task = tasks[i];
                System.out.println(taskNum + "." + task);
            }
            System.out.println("You have " + numTasks + " task" + (numTasks != 1 ? "s" : "") + " in the list.");
        }
    }

    public Task getTask(int taskNum) {
        if (taskNum <= numTasks) {
            return tasks[taskNum - 1];
        } else {
            System.out.println("No such task!");
            return null;
        }
    }
}
