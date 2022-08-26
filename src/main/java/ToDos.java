import java.time.format.DateTimeParseException;

public class ToDos extends Task {
    /**
     * Constructor that creates ToDos object with specified description and isDone status.
     *
     * @param description Description of ToDos.
     * @param isDone isDone status of the ToDos.
     */
    public ToDos(String description, boolean isDone) {
        super(isDone);
        this.description = description;
    }

    /**
     * Method converts ToDos into String representation that is stored in the storage.
     *
     * @return String representation of ToDos.
     */
    @Override
    public String processData() {
        String str;
        if (this.getIsDone()){
            str = String.format("T|true|%s|0|", this.getDescription());
        } else {
            str = String.format("T|false|%s|0|", this.getDescription());
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()){
            str = String.format("[T] %s [X]", this.getDescription());
        } else {
            str = String.format("[T] %s [ ]", this.getDescription());
        }
        return str;
    }

//    public ToDos(String description) throws MissingDescriptionException {
//        super();
//        try {
//            String substring = description.substring(5);
//            this.description = substring;
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new MissingDescriptionException();
//        }
//    }
}
