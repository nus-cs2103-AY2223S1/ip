package maria.task;

public class TaskTodo extends Task{

    public TaskTodo(String name, boolean isDone) throws TaskNoNameException {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "todo";
    }

}