import java.util.List;
import java.util.Scanner;

public class TaskTodo extends Task{

    public TaskTodo(String name, boolean done) throws TaskNoNameException {
        super(name, done);
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