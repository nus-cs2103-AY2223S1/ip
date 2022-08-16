public class DukeException extends Exception {
    protected String description;

    public DukeException() { // Default DukeException for invalid input
        super();
        this.description = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public DukeException(String task) { // DukeException for missing task descriptor
        super();
        this.description = " ☹ OOPS!!! The description of a " + task + " cannot be empty.";
    }

    public String getDescription() {
        return this.description;
    }
}
