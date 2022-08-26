package arguments;

import exceptions.DukeException;
import input.Input;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for datetime argument used by deadline e.g 2019-10-15 1800
 */
public class DatetimeArgument extends CompulsoryArgument<LocalDateTime> {
    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern("dd-MM-yyyy kkmm");
    private static final String ARG_NAME = "by";

    protected DatetimeArgument(Input input) {
        super(input, ARG_NAME, String.format("This command needs a datetime argument! e.g /%s 26-08-2022 0900",
                ARG_NAME));
    }

    @Override
    public void validate() throws DukeException {
        if (super.value != null) {
            return;
        }
        super.validate();

        String arg = input.getParameter(super.argumentName).trim();

        if (arg.equals("")) {
            throw new DukeException("Datetime should not be empty!");
        }

        try {
            super.value = LocalDateTime.parse(arg, DATE_PARSER);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Incorrect date format provided :(");
        }
    }
}
