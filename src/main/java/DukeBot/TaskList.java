package DukeBot;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void addTask(Task newTask) {
        this.add(newTask);
    }

    public Task deleteTask(int taskToDelete) {
        Task deletedTask = this.remove(taskToDelete);
        Task.reduceTaskCount();
        return deletedTask;
    }
}
