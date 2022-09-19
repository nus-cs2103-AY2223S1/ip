package duke;

import duke.task.Deadline;
import duke.task.Event;

import java.util.Comparator;

public class LocalDateComparator implements Comparator<Deadline> {
    @Override
    public int compare(Deadline o1, Deadline o2) {
        int result = o1.getDate().compareTo(o2.getDate());
        result = ((-1) * result);
        return result;
    }
}
