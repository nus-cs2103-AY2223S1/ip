package ip.exception;

public class BadLineFormat extends DukeException {

    String badLine;

    public BadLineFormat(String badLine) {
        this.badLine = badLine;
    }

    @Override
    public String toString() {
        return "Line with wrong format: " + badLine;
    }

}
