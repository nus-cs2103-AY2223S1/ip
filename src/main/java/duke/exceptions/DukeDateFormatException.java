package duke.exceptions;

public class DukeDateFormatException extends DukeException {
    String invalidDate;
    public DukeDateFormatException(String invalidDate) {
        super("Required format: yyyy-mm-dd");
        this.invalidDate = invalidDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Date found: %s", invalidDate);
    }

}
