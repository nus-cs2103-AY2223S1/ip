package roofus.task;

import java.time.LocalDate;

public class Event extends Task {
    LocalDate start;

    public Event(String description,String start) {
        super(description);
        this.start = LocalDate.parse(start);
    }

    @Override
    public String writeString() {
        return String.format("E | %d | %s | %s", super.isDone ? 1 : 0, 
                super.description, start);
    }

    @Override
    public String toString() {
        return String.format("[E]%s at: %s", super.toString(), start.toString());
    }
}
