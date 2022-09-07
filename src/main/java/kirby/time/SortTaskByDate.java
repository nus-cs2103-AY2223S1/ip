package kirby.time;

import java.util.Comparator;

import kirby.tasks.Task;
class SortTaskByDate implements Comparator<Task> {
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
