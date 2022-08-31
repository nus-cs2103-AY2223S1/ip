package models;

public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T" + super.toSave() + this.description;
    }
}
