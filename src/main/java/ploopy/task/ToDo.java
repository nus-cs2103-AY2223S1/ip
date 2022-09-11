package ploopy.task;

public class ToDo extends Task {

    /**
     * Constructor that takes a name.
     * @param name Name of the task.
     */
    public ToDo(String name) {
        super(name, null);
        this.type = "T";
    }

    @Override
    public String toString() {
        return String.format("%s%s", super.toString(), getPriorityForString());
    }

    @Override
    public String getDate() {
        return "";
    }

    /**
     * Returns the string representation of the ToDo.
     *
     * @return the String representation of the ToDo.
     */
    @Override
    public String getDateForFileWrite() {
        return "";
    }

}
