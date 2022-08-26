package pixel.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private final DateTimeFormatter dateTimeFormatter;

    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateTimeFormatter = dateFormatter;
    }

    public boolean isValid(String dateStr) {
        try {
            this.dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}

// Unit tests
/*
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
    .withResolverStyle(ResolverStyle.STRICT);
DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

assertTrue(validator.isValid("2019-02-28"));
assertFalse(validator.isValid("2019-02-30"));
    */
