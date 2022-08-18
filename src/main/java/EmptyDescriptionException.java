public class EmptyDescriptionException extends DukeException{
    String typeOf;
    public EmptyDescriptionException(String message, String typeOf) {
        super(message);
        this.typeOf = typeOf;
    }

    @Override
    public String toString() {
        return "OPS!!! The description of a " + this.typeOf + " cannot be empty.";
    }

}
