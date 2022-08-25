import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    
    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(List<Task> existTasks) {
            tasks = existTasks;
    }
}
