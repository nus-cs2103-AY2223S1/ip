package duke.util;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;

import java.time.Instant;

/**
 * A simple JChronic-based date parser.
 */

public class NaturalDateParser {
    public static Instant parse(String input) throws DateNotFoundException {
        Options options = new Options(Pointer.PointerType.FUTURE);
        Span parsedDate = Chronic.parse(input, options);
        if (parsedDate == null) {
            throw new DateNotFoundException(input);
        }
        return parsedDate.getBeginCalendar().toInstant();
    }

    public static class DateNotFoundException extends IllegalArgumentException {
        private final String parsedString;

        public DateNotFoundException(String parsedString) {
            super("Cannot find a date in " + parsedString);
            this.parsedString = parsedString;
        }

        public String getParsedString() {
            return parsedString;
        }
    }
}
