public class TaskList {
    private static Task[] taskList;
    private static int numOfTasks;

    public TaskList() {
        taskList = new Task[100];
        numOfTasks = 0;
    }

    public void addTask(String s) {
        taskList[numOfTasks] = new Task(s);
        numOfTasks++;
    }

    public String readTask(int index) {
        return taskList[index].getDescription();
    }

    public String readStatus(int index) {
        return taskList[index].getStatus();
    }

    public void setCompleted(int index) {
        taskList[index].markAsCompleted();
    }

    public void setNotCompleted(int index) {
        taskList[index].markAsNotCompleted();
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

}
