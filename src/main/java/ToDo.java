public class ToDo extends Task{

    /**
     * Constructor for ToDo Class.
     * @param name String representation of task name.
     */
    public ToDo(String name) {
        this.name = name;
        this.isDone = false;;
    }

    /**
     * Returns string representation of ToDo object.
     * @return String representation of ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s", "T", status, name);
    }

}
