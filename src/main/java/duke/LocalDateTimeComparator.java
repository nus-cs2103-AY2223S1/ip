package duke;

import duke.task.Event;

import java.util.Comparator;

public class LocalDateTimeComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        int result = o1.getLocalDateTime().toLocalDate().compareTo(o2.getLocalDateTime().toLocalDate());
        result = ((-1) * result);
        if (0 == result) {
            result = o1.getLocalDateTime().toLocalTime().compareTo(o2.getLocalDateTime().toLocalTime());
        }
        return result;
    }
}
