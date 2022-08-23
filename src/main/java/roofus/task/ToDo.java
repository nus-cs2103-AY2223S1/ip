package roofus.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeString() {
        return String.format("T | %d | %s", 
                super.isDone ? 1 : 0, super.description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
