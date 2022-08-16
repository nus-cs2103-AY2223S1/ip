public class Todo extends Task {
    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[T][X]" : "[T][ ]";
        return checkbox + " " + super.getDescription();
    }
}
