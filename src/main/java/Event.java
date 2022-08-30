import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("E;%s;%s;%s\n", getStatusIcon(), description, at));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
