import java.util.List;

public class Event extends Task{
    Event(List<String> task) throws DekuExceptions {
        super(task, "event", "E");
    }

}
