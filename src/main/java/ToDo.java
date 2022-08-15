/**
 * A task without any date/time attached to it e.g., visit new theme park
 */
public class ToDo extends Task {

    public ToDo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
