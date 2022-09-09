package jarvis.task;

import java.util.Comparator;

/**
 * TaskComparator --- compare tasks by their deadlines and starting times.
 */
public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo) {
            return -1;
        } else if (t2 instanceof ToDo) {
            return 1;
        } else {
            if (t1 instanceof Deadline) {
                Deadline d1 = (Deadline) t1;
                if (t2 instanceof Deadline) {
                    Deadline d2 = (Deadline) t2;
                    return d1.getDueBy().compareTo(d2.getDueBy());
                } else {
                    Event e2 = (Event) t2;
                    return d1.getDueBy().compareTo(e2.getStartAt());
                }
            } else {
                Event e1 = (Event) t1;
                if (t2 instanceof Deadline) {
                    Deadline d2 = (Deadline) t2;
                    return e1.getStartAt().compareTo(d2.getDueBy());
                } else {
                    Event e2 = (Event) t2;
                    return e1.getStartAt().compareTo(e2.getStartAt());
                }
            }
        }
    }
}
