package ip.exception;

/**
 * Exteption thrown when lines in save file are incorrectly formatted.
 */
public class BadLineFormat extends DukeException {

    private String badLine;

    public BadLineFormat(String badLine) {
        this.badLine = badLine;
    }

    @Override
    public String toString() {
        return "Line with bad format: " + badLine;
    }

}
