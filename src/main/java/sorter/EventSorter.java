package sorter;

import java.util.Comparator;

import task.Event;

/**
 * <h1>EventSorter class</h1>
 * sorts the Event tasks according to the earliest task timing.
 */
public class EventSorter implements Sorter {
    private Comparator<Event> comparator = new Comparator<Event>() {
        @Override
        public int compare(Event first, Event second) {
            return first.getLocalTime().compareTo(second.getLocalTime());
        }
    };

    @Override
    public Comparator<Event> getComparator() {
        return comparator;
    }
}
