package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task{
    private String during;
    private LocalDateTime dateTime;
    private String timeText;

    public Deadline(ParsedData parsedData) {
        super(parsedData);
        this.during = parsedData.getDuring();
        if (parsedData.hasDateTime()) {
            this.dateTime = parsedData.getDateTime();
        }
        this.timeText = parsedData.getTimeText();
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getTypeLetter() {
        return "D";
    }

    @Override
    public String getDuring() {
        return this.during;
    }

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
