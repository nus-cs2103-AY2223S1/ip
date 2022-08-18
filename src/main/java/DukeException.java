public class DukeException extends RuntimeException {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    public DukeException() {
        this.message = "I'm sorry, but I don't know what that means :-(";
    }

    public String toString() {
        return "Wake up your idea! " + message;
    }
}
