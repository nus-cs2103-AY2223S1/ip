public class Todo extends Task{
    public Todo(String description) {
        super(description);
        this.type = Type.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
