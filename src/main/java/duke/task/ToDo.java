package duke.task;

public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
