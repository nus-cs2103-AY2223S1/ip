package mort.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Utility class for TaskListTest
 */
public class TaskListTestUtil {
    private ArrayList<Task> tasks;
    private TaskList taskList;
    private final LocalDateTime dateTime1 = LocalDateTime.parse("16/9/2022 1200",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDateTime dateTime2 = LocalDateTime.parse("1/10/2022 1000",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDateTime dateTime3 = LocalDateTime.parse("3/10/2022 1400",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDate date1 = dateTime1.toLocalDate();
    private final LocalDate date2 = dateTime2.toLocalDate();
    private final LocalDate date3 = dateTime3.toLocalDate();

    /**
     * Constructor to initialize tasks in task list.
     */
    public TaskListTestUtil() {
        tasks = new ArrayList<>();
        tasks.add(new ToDo("buy 6 apples & chicken", false));
        tasks.add(new Deadline("spanish video project", dateTime1, true));
        tasks.add(new Event("CS2106 midterm", dateTime2, false));
        tasks.add(new Deadline("french video project", date1, false));
        tasks.add(new Event("Ann's Birthday", date2, true));
        taskList = new TaskList(tasks);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getTask(int index) {
        return tasks.get(index).toString();
    }

    public Task getNewTask(String taskType) {
        switch (taskType) {
        case "T":
            return new ToDo("get new kicks");
        case "D":
            return new Deadline("CS2106 lab 2", date2);
        case "E":
            return new Event("CS2105 Midterm", dateTime3);
        default:
            return null;
        }
    }

    public LocalDate getDate(int index) {
        switch (index) {
        case 1:
            return date1;
        case 2:
            return date2;
        case 3:
            return date3;
        default:
            return null;
        }
    }
}
