package duke.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

public class TaskDateTime {
    private HashMap<LocalDateTime, List<Task>> taskDates;

    public TaskDateTime(HashMap<LocalDateTime, List<Task>> taskDates) {
        this.taskDates = taskDates;
    }
}
