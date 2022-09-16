package duke.comparator;

import java.util.Comparator;

import duke.task.Task;

public class TaskTypeComparator implements Comparator<Task> {


    @Override
    public int compare(Task o1, Task o2) {
        return o1.getClass().toString().compareTo(o2.getClass().toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaskTypeComparator)) {
            return false;
        }

        return true;
    }
}
