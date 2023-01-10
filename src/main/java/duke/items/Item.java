package duke.items;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Abstract Item class to construct actual items on
 */
public abstract class Item {
    private boolean isDone = false;
    private final ItemTypes itemType;
    private boolean isTimed;
    private LocalDateTime time;
    private final String name;

    /**
     * Creates an incomplete Item.
     * @param name Item description.
     * @param itemType Type of the item.
     * @param time Time of the item.
     * @throws DateTimeParseException If the input string for time is not in correct format.
     */
    public Item(String name, ItemTypes itemType, String time) throws DateTimeParseException {
        this.name = name;
        this.itemType = itemType;
        this.setLocalDateTime(time);
    }

    /**
     * Create an Item and set its completion status.
     * @param name Item description.
     * @param isDone Completion status of the task.
     * @param itemType Type of the item.
     * @param time Time of the item.
     * @throws DateTimeParseException If the input string for time is not in correct format.
     */
    public Item(String name, boolean isDone, ItemTypes itemType, String time) throws DateTimeParseException {
        this.name = name;
        this.isDone = isDone;
        this.itemType = itemType;
        this.setLocalDateTime(time);
    }

    protected LocalTime getTime() {
        return this.time != null ? isTimed ? this.time.toLocalTime() : null : null;
    }

    protected LocalDate getDate() {
        return this.time != null ? this.time.toLocalDate() : null;
    }

    public String getDateTimeString() {
        if (this.time == null) {
            return null;
        }
        return this.getDate().toString() + ((this.getTime() != null) ? " " + this.getTime().toString() : "");
    }

    public ItemTypes getItemType() {
        return this.itemType;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    /**
     * Parses the string for item time and sets it in the item.
     * @param str input string for item time.
     * @throws DateTimeParseException If the input string for time is not in correct format.
     */
    protected void setLocalDateTime(String str) throws DateTimeParseException {
        if (str == null) {
            isTimed = false;
            return;
        }

        str = str.trim();
        String[] splitInput = str.split(" ", 2);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate date = LocalDate.parse(splitInput[0].trim(), dateFormatter);
        if (splitInput.length == 2) {
            this.isTimed = true;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(splitInput[1].trim(), timeFormatter);
            this.time = date.atTime(time);
        } else {
            this.isTimed = false;
            this.time = date.atStartOfDay();
        }
    }

    @Override
    public String toString() {
        String done = "[X] ";
        String unDone = "[ ] ";
        return (this.isDone ? done : unDone) + this.name;
    }
}
