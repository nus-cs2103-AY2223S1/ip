package jarvis.task;

import java.util.Comparator;

/**
 * TaskComparator --- compare tasks by their deadlines and starting times.
 */
public class TaskComparator implements Comparator<Task> {
    int compareDeadlines(Deadline d1, Deadline d2) {
        return d1.getDueBy().compareTo(d2.getDueBy());
    }

    int compareEvents(Event e1, Event e2) {
        return e1.getStartAt().compareTo(e2.getStartAt());
    }

    int compareDeadlineAndEvent(Deadline d, Event e) {
        return d.getDueBy().compareTo(e.getStartAt());
    }

    int compareEventAndDeadline(Event e, Deadline d) {
        return e.getStartAt().compareTo(d.getDueBy());
    }
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo) {
            return -1;
        } else if (t2 instanceof ToDo) {
            return 1;
        } else {
            if (t1 instanceof Deadline) {
                if (t2 instanceof Deadline) {
                    return compareDeadlines((Deadline) t1, (Deadline) t2);
                }
                return compareDeadlineAndEvent((Deadline) t1, (Event) t2);
            } else {
                Event e1 = (Event) t1;
                if (t2 instanceof Deadline) {
                    return compareEventAndDeadline((Event) t1, (Deadline) t2);
                } else {
                    return compareEvents((Event) t1, (Event) t2);
                }
            }
        }
    }
}
