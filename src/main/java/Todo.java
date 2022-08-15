public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[T][X]";
        } else {
            return "[T][ ]";
        }
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description;
    }
}
