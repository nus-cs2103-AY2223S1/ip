package duke.task;

/**
 * Represents a deadline that has a name and a date that it should be done by
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo Object
     *
     * @param name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * writeData method that formats the information in todo
     *
     * @return String
     */
    @Override
    public String writeData() {
        int mark = isDone ? 1 : 0;
        return "T#" + mark + "#" + this.name;
    }

    /**
     * toString method that formats the information in todo
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
