/**
 * Encapsulates a task without any date/time attached to it, e.g. visit new theme park
 *
 * @author Emily Ong Hui Qi
 */

public class ToDo extends Task {
    private static final String TASK_TYPE_ICON = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return ToDo.TASK_TYPE_ICON;
    }
}
