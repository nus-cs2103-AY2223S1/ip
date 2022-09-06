package duke;

import java.time.LocalDate;

public interface Dated {
    boolean isBetween(LocalDate start, LocalDate end);
}
