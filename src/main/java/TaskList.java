public class TaskList {
    Task[] taskList = new Task[100];
    // The first index that is empty
    private int currLastIndex = 0;

    public void addTask(String description, Task.TaskType taskType) {
        taskList[currLastIndex] = new Task(description, taskType);
        ++currLastIndex;
        System.out.println("added: " + description);
    }

    public void markTaskAsDone(int taskNum) {
        taskList[taskNum].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[taskNum].toString());
    }

    public void printTasks() {
        for (int i = 0; i < currLastIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
    }
}
