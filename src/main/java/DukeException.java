public class DukeException extends Exception {
    private String userInput;

    public DukeException(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String toString() {
        return "Invalid input: " + "\"" + userInput + "\"";
    }
}
