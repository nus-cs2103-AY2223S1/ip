package dukeprogram.tasks;

import dukeprogram.userinterface.TextStyle;
import dukeprogram.userinterface.WidgetTaskLabel;

/**
 * A Deadline task with a date that describes when the task is due
 */
public class Deadline extends TaskWithDuration {

    /**
     * Creates a Deadline class with the given name
     * and a date string that it is due by
     * @param name the name of this task
     * @param dueString the due date of this string
     */
    public Deadline(String name, String dueString) {
        super(name, dueString, "by");
    }

    public Deadline() {
        super();
    }


    /**
     * Creates a widget label for this deadline task
     * @return the WidgetTaskLabel for this deadline
     */
    @Override
    public WidgetTaskLabel createLabelWidget() {
        WidgetTaskLabel label = new WidgetTaskLabel("deadline",
                getName(), TextStyle.Regular, getTaskState());
        label.addInfoLabel(getTimeString());
        return label;
    }

    /**
     * Creates a string tagged with the Deadline tag
     * @return a string that in the format "[D][status] task_name /by date_format"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
