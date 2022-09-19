package duke.task;

import java.time.LocalDateTime;
import java.util.Comparator;

public class LocalDateTimeComparator implements Comparator<LocalDateTime> {
    @Override
    public int compare(LocalDateTime o1, LocalDateTime o2) {
        int result = o1.toLocalDate().compareTo(o2.toLocalDate());
        result = ((-1) * result);
        if (0 == result) {
            System.out.println( "reversing " );
            result = o1.toLocalTime().compareTo(o2.toLocalTime());
        }
        return result;
    }
}
