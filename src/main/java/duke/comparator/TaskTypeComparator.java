package duke.comparator;

import duke.task.Task;
import duke.task.ToDo;

import java.util.Comparator;

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
