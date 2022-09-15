package duke;

import java.time.LocalDate;

/**
 * An interface used by Tasks with LocalDate objects (Deadline, Event).
 * Supports isBetween() method.
 */
public interface Dated {
    boolean isBetween(LocalDate start, LocalDate end);
}
