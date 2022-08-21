public class Todo extends Task{

    public Todo(String description) {
        super(description);
        super.numberOfTasks += 1;
    }

    public String getSaveData() {
        return "T|" + (super.isDone ? 1 : 0) + "|" + super.description;
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
