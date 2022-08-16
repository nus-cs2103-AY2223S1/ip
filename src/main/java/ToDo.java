public class ToDo extends Task{

    /**
     * Constructor for ToDo Class.
     * @param name String representation of task name.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns string representation of ToDo object.
     * @return String representation of ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
