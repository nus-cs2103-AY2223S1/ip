package duke.task;

import java.util.Comparator;

/**
 * An instance of a comparator between task dates
 */
public class TaskDateComparator implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        if (b.getLocalDateTime().isBefore(a.getLocalDateTime())) {
            return 1;
        } else if (b.getLocalDateTime().isEqual(a.getLocalDateTime())) {
            return 0;
        } else {
            return -1;
        }
    }
}
