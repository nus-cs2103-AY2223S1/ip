package Duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public List<String> toList() {
        List<String> product = new ArrayList<>();
        product.add("T");
        product.addAll(super.toList());
        return product;
    }

    @Override
    public String fileFormat() {
        return String.format("todo | %s | %b", super.description, super.isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
