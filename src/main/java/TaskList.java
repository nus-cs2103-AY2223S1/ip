public class TaskList {
    private final Task[] taskList;
    private int size;

    public TaskList() {
        this.taskList = new Task[100];
        this.size = 0;
    }

    public String addTask(String taskName) {
        taskList[this.size] = new Task(taskName);
        this.size++;
        return "added: " + taskName;
    }

    public String markTask(int taskNumber) {
        Task task = taskList[taskNumber - 1];
        task.mark(true);
        return "Nice! I've marked this task as done:\n\t" + task;
    }

    public String unmarkTask(int taskNumber) {
        Task task = taskList[taskNumber - 1];
        task.mark(false);
        return "Ok, I've marked this task as not done yet:\n\t" + task;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= this.taskList.length; i++) {
            Task task = this.taskList[i-1];
            if (task == null) {
                break;
            }
            String numberedTask = i + "." + task + "\n";
            result.append(numberedTask);
        }
        return result.toString();
    }
}
