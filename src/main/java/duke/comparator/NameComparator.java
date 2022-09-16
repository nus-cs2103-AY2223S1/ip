package duke.comparator;

import duke.task.Task;
import duke.task.ToDo;

import java.util.Comparator;

public class NameComparator implements Comparator<Task> {


    @Override
    public int compare(Task o1, Task o2) {

        return o1.getTask().compareTo(o2.getTask());

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NameComparator)) {
            return false;
        }

        return true;
    }
}
