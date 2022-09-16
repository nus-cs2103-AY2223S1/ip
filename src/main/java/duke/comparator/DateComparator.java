package duke.comparator;

import java.util.Comparator;

import duke.task.Task;

public class DateComparator implements Comparator<Task> {


    @Override
    public int compare(Task o1, Task o2) {

        return o1.getParsedDate().getDateTime().compareTo(o2.getParsedDate().getDateTime());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateComparator)) {
            return false;
        }

        return true;
    }
}
