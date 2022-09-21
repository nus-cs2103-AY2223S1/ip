package anya;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Represents a class that do time comparisons.
 */
public class TimeComparator implements Comparator<Deadline> {

    /**
     * Compare the deadline's time.
     */
    public int compare(Deadline deadline1, Deadline deadline2) {
        int booleanValue = 0;
        LocalDate d1 = deadline1.getTime();
        LocalDate d2 = deadline2.getTime();
        if (d1.isBefore(d2)) {
            booleanValue = -1;
        } else if (d1.isAfter(d2)) {
            booleanValue = 1;
        }
        return booleanValue;
    }

}

