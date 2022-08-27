import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> l) {
        this.tasks = l;
    }

    public TaskList(TaskList l) {
        this.tasks = l.tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void printTaskList() {
        int count = 1;
        for (Task t : tasks) {
            System.out.println(String.format("%d.%s %s", count, t.getStatusIcon(),
                    t.getDescription()));
            count += 1;
        }
    }

    public String saveTaskList() {
        String result = "Task Type | Status | Description & Time\n";
        for (Task t : tasks) {
            result += t.toString() + "\n";
        }
        return result;
    }

    public Integer getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task deleteTaskAtIndex(int index) {
        return this.tasks.remove(index - 1);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task markAsDone(int index) {
        Task t = this.tasks.get(index - 1);
        t.markAsDone();
        return t;
    }

    public Task markAsUndone(int index) {
        Task t = this.tasks.get(index - 1);
        t.markAsUndone();
        return t;
    }

    public TaskList searchByDate(LocalDate date) {
        List<Task> tasksAtDate = new ArrayList<Task>();
        for (Task t : tasks) {
            if (!t.getStatus() && t.taskType().equals("event")) {
                Event e = (Event) t;
                if ((date.isAfter(e.getStartDate()) && date.isBefore(e.getEndDate()))
                        || date.isEqual(e.getStartDate()) || date.isEqual(e.getEndDate())) {
                    tasksAtDate.add(e);
                }
            } else if (!t.getStatus() && t.taskType().equals("deadline")) {
                Deadline d = (Deadline) t;
                if (!date.isAfter(d.getEnd())) {
                    tasksAtDate.add(d);
                }
            }
        }
        return new TaskList(tasksAtDate);
    }

}
