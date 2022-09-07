package duke.task;

public class Note extends Task {
    private String note;
    /**
     * Creates a <code>Note</code> object.
     *
     * @param note note description
     */
    public Note(String note) {
        super(note);
        this.note = note;
    }

    /**
     * Returns this <code>Task</code> in CSV format.
     *
     * @return CSV representation of this Task
     */
    @Override
    public String toCsv() {
        return "N," + super.toCsv() + "\n";
    }

    /**
     * Returns a string representation of this <code>Task</code>.
     *
     * @return a string representation of this <code>Task</code>
     */
    @Override
    public String toString() {
        return note;
    }
}
