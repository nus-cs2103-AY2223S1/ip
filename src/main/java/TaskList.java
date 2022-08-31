import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;
    
    TaskList() {
        taskList = new ArrayList<>();
    }

    TaskList(List<Task> existTasks) {
            taskList = existTasks;
    }
}
