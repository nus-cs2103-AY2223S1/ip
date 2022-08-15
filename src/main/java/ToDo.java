public class ToDo extends Task {
    public ToDo(String name) {
        /**
         * Constructor for ToDo class. Sets the ToDo name.
         *
         * @param name The ToDo name.
         */
        super(name);
    }

    public String toString() {
        /**
         * String representation of a ToDo. Also indicates if the ToDo is done.
         */
        return "[T]" + super.toString();
    }
}
