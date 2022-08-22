import java.util.List;

public class Deadline extends Task{
    Deadline(List<String> task) throws DekuExceptions {
        super(task, "deadline", "D");
    }
}
