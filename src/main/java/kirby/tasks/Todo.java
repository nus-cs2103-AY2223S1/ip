package kirby.tasks;

/**
 * The kirby.Todo class contains information of a kirby.Todo task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public int[] getDate() {
        return new int[]{-1, -1, -1};
    }

    @Override
    public String toString() {
        return "[T] " + this.getStatusIcon() + " " + this.description ;
    }

    @Override
    public String toFileOutput() {
        return "kirby.Todo~" + this.description;
    }
}