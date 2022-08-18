/**
 * Todos are tasks without any date/time attached to it e.g., visit new theme park
 */
public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}