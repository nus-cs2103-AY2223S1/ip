package duke;

public class EmptyDescriptionException extends DukeException{
    String typeOf;

    /**
     * Constructor for the empty description exception.
     *
     * @param message Message of exception
     * @param typeOf Type of task whose description is empty.
     */
    public EmptyDescriptionException(String message, String typeOf) {
        super(message);
        this.typeOf = typeOf;
    }

    @Override
    public String toString() {
        return "OPS!!! The description of a " + this.typeOf + " cannot be empty.";
    }

}
