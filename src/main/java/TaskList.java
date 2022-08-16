public class TaskList {
    private final String[] taskList;
    private int size;

    public TaskList() {
        taskList = new String[100];
        size = 0;
    }

    public String addTask(String task) {
       taskList[size] = task;
       size++;
       return "added: " + task;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= taskList.length; i++) {
            String task = taskList[i-1];
            if (task == null) {
                break;
            }
            String numberedTask = i + ". " + task + "\n";
            result.append(numberedTask);
        }
        return result.toString();
    }
}
