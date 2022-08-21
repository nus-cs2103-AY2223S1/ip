/**
 * The Todo class contains information of a Todo task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + this.getStatusIcon() + " " + this.description ;
    }

    @Override
    public String toFileOutput() {
        return "Todo~" + this.description;
    }
}