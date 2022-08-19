public class Todo extends Task {

    /**
     * Constructor for a To-do item.
     *
     * @param name Description of task
     */
    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
