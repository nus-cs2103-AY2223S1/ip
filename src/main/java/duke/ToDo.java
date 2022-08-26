package duke;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    /*
     * @return String representation in
     *         "[T] [status] task"
     */
    @Override
    public String toString() {
        return "[T] "
                + this.getStatusIcon()
                + " "
                + super.description;
    }

    /*
     * return String representation to be stored in Storage
     * @return String representation in
     *         "T|0 or 1|task"
     *         where 1 represents the task is marked and 0 otherwise
     */
    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "T|1|"
                    + super.description
                    + "\n";
        } else {
            return "T|0|"
                    + super.description
                    + "\n";
        }
    }
}
