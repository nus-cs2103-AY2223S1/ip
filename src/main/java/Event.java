import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("E;%s;%s;%s\n", getStatusIcon(), description, at));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
