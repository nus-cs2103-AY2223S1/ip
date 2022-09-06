package duke.util;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.tags.Pointer;

import java.time.Instant;

/** A simple JChronic-based date parser. */

public class NaturalDateParser {
    public static Instant parse(String input) {
        Options options = new Options(Pointer.PointerType.FUTURE);
        return Chronic.parse(input, options).getBeginCalendar().toInstant();
    }
}
