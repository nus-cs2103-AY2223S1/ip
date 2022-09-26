package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a kind of Task.
 * @author Lan Jingbo, Jerry
 */
class Event extends Task{
    protected LocalDate time;

    /**
     * The constructor of this kind of task.
     *
     * @param itself the descriptions of Task
     * @param time the deadline date of the task.
     */
    public Event(String itself, String time) {
        super(itself);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * To check if the task due in input date.
     *
     * @param localDate the input date
     * @return whether it is on the expected date
     */
    public boolean isOnDate(LocalDate localDate) {
        return time.equals(localDate);
    }

    /**
     * convert the task to saving file form.
     * @return the saving file form
     */
    @Override
    public String writeToFile() {
        return "E|" + super.writeToFile() + "|" + time;
    }

    /**
     * convert the file information to concrete event task.
     *
     * @param input the string format in the .txt file
     * @return the concrete deadline
     */
    public static Event fromFileDescription(String input) {
        String[] strArray = input.split("\\|");
        String info = strArray[2];
        String at = strArray[3];
        Event event = new Event(info, at);
        if (strArray[1].equals("Y")) {
            event.donelah();
        }
        return event;
    }

    /**
     * Creates a String to represent the task during listing.
     *
     * @return String to be displayed when listing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                time.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
