package duke.task;

/**
 * Class which manages tasks of type do after.
 */
public class DoAfter extends Task {

    private String after;

    /**
     * Creates an instance of do after task.
     * @param desc description of task
     */
    public DoAfter(String desc) {
        super(desc);

        String[] parts = desc.split("/after ");
        super.description = parts[0];
        super.isDone = false;
        super.type = "A";

        try {
            after = parts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getTask() {
        String mark = " ";
        if (this.isDone) {
            mark = "X";
        }

        if (after == null) {
            throw new NullPointerException();
        } else {
            return "[" + type + "][" + mark + "]" + this.description + "(after: " + after + ")";
        }
    }
}
