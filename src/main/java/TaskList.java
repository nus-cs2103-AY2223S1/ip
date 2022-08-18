public class TaskList {
    String[] taskList = new String[100];
    // The first index that is empty
    private int currLastIndex = 0;

    public void addTask(String s) {
        taskList[currLastIndex] = s;
        ++currLastIndex;
        System.out.println("added: " + s);
    }

    public void printTasks() {
        for (int i = 0; i < currLastIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }
}
