package tasks;

import util.ParsedData;

import java.time.LocalDateTime;

/**
 * This class encapsulates an Event Task.
 */
public class Event extends Task {
    private String during;
    private LocalDateTime dateTime;
    private String timeText;

    /**
     * Constructor for Deadline
     *
     * @param parsedData Data used to create the Event.
     */
    public Event(ParsedData parsedData) {
        super(parsedData);
        this.during = parsedData.getDuring();
        if (parsedData.hasDateTime()) {
            this.dateTime = parsedData.getDateTime();
        }
        this.timeText = parsedData.getTimeText();
    }

    /**
     * Returns the type icon
     *
     * @return The type icon.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns the type letter
     *
     * @return The type letter.
     */
    @Override
    public String getTypeLetter() {
        return "E";
    }

    /**
     * Returns the time keyword
     *
     * @return The time keyword.
     */
    @Override
    public String getDuring() {
        return this.during;
    }

    /**
     * Returns the time text
     *
     * @return The time text.
     */
    @Override
    public String getTimeText() {
        return this.timeText;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s (%s: %s)",
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.during,
                this.getTimeText());
    }
}
