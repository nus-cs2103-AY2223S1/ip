package dukeprogram.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;

import dukeprogram.userinterface.TextStyle;
import dukeprogram.userinterface.WidgetTaskLabel;


/**
 * Event class for creating an event
 */
public class Event extends TaskWithDuration {

    @JsonProperty("endEvent")
    private TaskWithDuration endEvent;

    /**
     * Creates a new Event
     * @param name name of the event
     * @param startDate a date that describes the occurrence date of the event
     * @param endDate a date that describes the occurrence date of the event
     */
    public Event(String name, String startDate, String endDate) {
        super(name, startDate, "at");
        endEvent = new Deadline(name, endDate);
    }

    private Event() {
        super();
    }

    /**
     * Creates a widget label for this event
     * @return the WidgetTaskLabel for this event
     */
    @Override
    public WidgetTaskLabel createLabelWidget() {
        WidgetTaskLabel label = new WidgetTaskLabel("event",
                getName(), TextStyle.Regular, getTaskState());
        label.addInfoLabel(getTimeString());
        label.addInfoLabel(endEvent.getTimeString());
        return label;
    }

    /**
     * Returns a formatted string beginning with the event tag
     * followed by the task data
     * @return a string that begins with "[E]" followed by the task data
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
