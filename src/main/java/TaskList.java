import java.util.stream.Stream;

public class TaskList {

    private static final int MAX_TASKLIST_SIZE = 100;
    private Task[] taskList;
    private int taskCount;

    TaskList() {
        this.taskList = new Task[TaskList.MAX_TASKLIST_SIZE];
        this.taskCount = 0;
    }

    TaskList(Task... taskList) {
        // Should not occur based on task constraints.
        assert taskList.length <= MAX_TASKLIST_SIZE;
        this.taskList = new Task[TaskList.MAX_TASKLIST_SIZE];
        System.arraycopy(taskList, 0, this.taskList, 0, taskList.length);
    }

    public void addTask(Task task) {
        // Should not occur based on task constraints.
        assert taskCount < MAX_TASKLIST_SIZE;
        this.taskList[taskCount++] = task;

    }

    public String[] toStringList() {
        return Stream.of(this.taskList)
                .limit(this.taskCount)
                .map(Task::toString)
                .toArray(String[]::new);
    }

    public int getLength() {
        return this.taskCount;
    }

    // TODO: Add exception on out-of-bounds access
    public Task getTask(int index) {
        return this.taskList[index];
    }
}
