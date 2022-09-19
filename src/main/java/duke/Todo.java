package duke;

/**
 * A To-Do is a type of Task with only a name.
 */
public class Todo extends Task {

    /**
     * Constructs a To-Do.
     *
     * @param name Name of the To-Do.
     * @param isDone Whether the To-Do has been completed.
     * @throws DukeTaskException  If Task creation goes wrong.
     */
    public Todo(String name, boolean isDone) throws DukeTaskException {
        super(name, isDone);
    }

    /**
     * Loads a To-Do from the data given.
     *
     * @param s Data to construct the To-Do with.
     * @return To-Do constructed with the data given.
     * @throws DukeException  If a To-Do cannot be created.
     */
    public static Todo load(String s) throws DukeException {
        String name = s.substring(1, s.indexOf("|")).trim();
        String isDone = s.substring(s.indexOf("|") + 1).trim();
        return new Todo(name, Boolean.parseBoolean(isDone));
    }

    /**
     * Returns a String to save to the save file.
     *
     * @return String representing To-Do data.
     */
    @Override
    public String saveString() {
        return "T " + super.saveString();
    }

    /**
     * Returns the String representation of the To-Do.
     *
     * @return String representing the To-Do.
     */
    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[T]" + temp;
    }

}
