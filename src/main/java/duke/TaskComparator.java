package duke;

import java.util.Comparator;

/**
 * This class handles the comparing of tasks.
 *
 * This class is to be used to sort out the tasks before saving.
 */
public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Todo && t2 instanceof Todo) {
            return 0;
        } else if (t1 instanceof Todo) {
            // if only t1 is a To-do
            return -1;
        } else if (t2 instanceof Todo) {
            // only t2 is a To-do
            return 1;
        } else {
            // t1 and t2 are either deadlines or events
            return t1.getDeadline().compareTo(t2.getDeadline());
        }

    }
}
