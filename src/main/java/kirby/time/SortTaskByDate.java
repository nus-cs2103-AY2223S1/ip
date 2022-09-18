package kirby.time;

import java.util.Comparator;

import kirby.tasks.Task;

/**
 * SortTaskByDate class contains the method to compare two tasks by their dates
 * to use them for sorting.
 */
class SortTaskByDate implements Comparator<Task> {
    /**
     * Compares the dates of two tasks.
     *
     * @param t1 First task.
     * @param t2 Second task.
     * @return -1 if t1 is earlier than t2, 1 if t2 is earlier, otherwise 0.
     */
    @Override
    public int compare(Task t1, Task t2) {
        // Compare year
        if (t1.getDate()[2] < t2.getDate()[2]) {
            return -1;
        } else if (t2.getDate()[2] < t1.getDate()[2]) {
            return 1;
        } else {
            // Compare month
            if (t1.getDate()[1] < t2.getDate()[1]) {
                return -1;
            } else if (t2.getDate()[1] < t1.getDate()[1]) {
                return 1;
            } else {
                // Compare date
                return Integer.compare(t1.getDate()[0], t2.getDate()[0]);
            }
        }
    }
}
