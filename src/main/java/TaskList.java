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

    public void addTodo(String s) {
        taskList[numOfTasks] = new Todo(s);
        numOfTasks++;
    }

    public void addDeadline(String s) {
        int index = s.indexOf('/');
        String description = s.substring(0, index);
        String by = s.substring(index + 3);
        taskList[numOfTasks] = new Deadline(description, by);
        numOfTasks++;
    }
    public void addEvent(String s) {
        int index = s.indexOf('/');
        String description = s.substring(0, index);
        String at = s.substring(index + 3);
        taskList[numOfTasks] = new Event(description, at);
        numOfTasks++;
    }


    public String readTask(int index) {
        return taskList[index].toString();
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
