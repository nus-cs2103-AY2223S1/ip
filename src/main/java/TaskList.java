public class TaskList {
    protected Task[] tasks;
    protected int numTasks;

    public TaskList() {
        this.tasks = new Task[100];
        this.numTasks = 0;
    }

    public void addTask(String input) {
        tasks[numTasks++] = new Task(input);
        System.out.println("Task added: " + input);
    }

    public void displayTasks() {
        if (numTasks == 0) {
            System.out.println("Your task list is empty!");
        } else {
            System.out.println("Task list:");
            for (int i = 0; i < numTasks; i++) {
                int taskNum = i + 1;
                Task task = tasks[i];
                System.out.println(taskNum + "." + task.getStatusIcon() + task.description);
            }
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
