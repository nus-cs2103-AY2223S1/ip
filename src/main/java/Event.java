import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Event extends Task {
    protected LocalDate by;

    public Event(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(Arrays.stream(by.split(" ")).skip(1).collect(Collectors.joining("")), formatter);
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("\\[T\\]", "[E]") + " (by " + by.toString() + ")";
    }
}
