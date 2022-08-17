public class TaskList {
    private final Task[] taskList;
    private int size;

    public TaskList() {
        this.taskList = new Task[100];
        this.size = 0;
    }

    public String getRemainingTasks() {
        return String.format("Now you have %d tasks in the list.", size);
    }

    public String addTask(String taskName) {
        Task task = new Task(taskName);
        taskList[this.size] = task;
        this.size++;
        return "added: " + taskName;
    }

    public String addToDo(String taskName) {
        Task task = new ToDo(taskName);
        taskList[this.size] = task;
        this.size++;
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String addEvent(String taskName, String at) {
        Task task = new Event(taskName, at);
        taskList[this.size] = task;
        this.size++;
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String addDeadline(String taskName, String by) {
        Task task = new Deadline(taskName, by);
        taskList[this.size] = task;
        this.size++;
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
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
