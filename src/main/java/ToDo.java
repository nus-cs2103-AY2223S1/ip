import java.util.List;

public class ToDo extends Task {
    private final String ICON = "[T]";

    ToDo(List<String> task) {
        super(task);
    }

    @Override
    public String toString() {
        return this.ICON + super.toString();
    }
}
