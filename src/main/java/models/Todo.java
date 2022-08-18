package models;

/**
 * A child class Todo that inherits properties description and isDone from Task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSymbol() {
        return "T";
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[T]"  + super.toString();
    }
}
