package sorter;

import java.util.Comparator;

/**
 * Sorter interface that sorts the tasks according
 * to a criteria
 */
public interface Sorter {
    Comparator getComparator();
}
