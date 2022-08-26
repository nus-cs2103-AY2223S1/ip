package duke.task;

public class ToDos extends Task {
    /**
     * Constructor that creates duke.task.ToDos object with specified description and isDone status.
     *
     * @param description Description of duke.task.ToDos.
     * @param isDone isDone status of the duke.task.ToDos.
     */
    public ToDos(String description, boolean isDone) {
        super(isDone);
        this.description = description;
    }

    /**
     * Method converts duke.task.ToDos into String representation that is stored in the storage.
     *
     * @return String representation of duke.task.ToDos.
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

//    public duke.task.ToDos(String description) throws duke.dukeexception.MissingDescriptionException {
//        super();
//        try {
//            String substring = description.substring(5);
//            this.description = substring;
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new duke.dukeexception.MissingDescriptionException();
//        }
//    }
}
