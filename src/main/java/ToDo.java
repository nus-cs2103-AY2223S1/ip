/**
 * One of the task types, specifically those with
 * no specific timing or deadline.
 */
public class ToDo extends Task {

    /**
     * Constructor method.
     *
     * @param description The description of this to-do
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * The stringtified version of this to-do.
     *
     * @return The print format of this to-do
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}