public class Todo extends Task {

    /**
     * Constructor for a To-do item.
     *
     * @param name Description of task
     */
    Todo(String name) {
        super(name);
    }

    Todo(String[] data) {
        super(data[2], (data[1].equals("1")));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringWritable() {
        return " T |" + super.toStringWritable();
    }
}
