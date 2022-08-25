import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(Arrays.stream(by.split(" ")).skip(1).collect(Collectors.joining("")), formatter);
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("\\[T\\]", "[D]") + " (on " + by.toString() + ")";
    }
}
