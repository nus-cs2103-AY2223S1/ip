public class TaskList {
    private static String[] taskList;
    private static int numOfTasks;

    public TaskList() {
        taskList = new String[100];
        numOfTasks = 0;
    }

    public void addTask(String s) {
        taskList[numOfTasks] = s;
        numOfTasks++;
    }

    public String readTask(int index) {
        return taskList[index];
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

}
