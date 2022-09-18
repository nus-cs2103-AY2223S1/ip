package drake.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Event extends Task {
    protected final LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public List<String> toList() {
        List<String> result = new ArrayList<>();
        result.add("E");
        result.addAll(super.toList());
        result.add(at.toString());
        return result;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
