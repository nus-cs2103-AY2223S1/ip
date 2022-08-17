public class DukeInvalidFormatException extends DukeException {

    public DukeInvalidFormatException(String type, String missingExpr) {
        super(type + " should have a " + missingExpr + " command and a specified date in its description");
    }

}
