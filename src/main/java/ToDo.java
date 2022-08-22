import java.util.List;

public class ToDo extends Task{
    ToDo(List<String> task) throws DekuExceptions {
        super(task, "todo","[T]");
    }
}
