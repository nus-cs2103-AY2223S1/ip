public class TaskList {
    private final String[] taskList;
    private int size;

    public TaskList() {
        this.taskList = new String[100];
        this.size = 0;
    }

    public String addTask(String task) {
       taskList[this.size] = task;
       this.size++;
       return "added: " + task;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= this.taskList.length; i++) {
            String task = this.taskList[i-1];
            if (task == null) {
                break;
            }
            String numberedTask = i + ". " + task + "\n";
            result.append(numberedTask);
        }
        return result.toString();
    }
}
