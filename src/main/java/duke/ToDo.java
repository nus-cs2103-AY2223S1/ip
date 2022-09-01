package duke;
public class ToDo extends Task {
    /**
     *
     * @param desc description of the task
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     *
     * @return String : [T][ ] with description of the task
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description.substring(5);
    }
}
