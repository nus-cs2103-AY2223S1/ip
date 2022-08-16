public class InvalidInput extends DukeException {
    InvalidInput(String message) {
        super("Invalid Input! " + message);
    }
}
