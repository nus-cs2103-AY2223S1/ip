import java.util.List;

public class ToDo extends Task {
    private final String ICON = "[T]";

    ToDo(List<String> task) throws DekuExceptions {
        super(task, "todo");
    }

    @Override
    public String toString() {
        return this.ICON + super.toString();
    }
}
