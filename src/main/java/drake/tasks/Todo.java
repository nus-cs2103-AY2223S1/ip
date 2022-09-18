package drake.tasks;

import java.util.ArrayList;
import java.util.List;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public List<String> toList() {
        List<String> result = new ArrayList<>();
        result.add("T");
        result.addAll(super.toList());
        return result;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
