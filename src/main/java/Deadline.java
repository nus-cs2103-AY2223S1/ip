import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("D;%s;%s;%s\n", getStatusIcon(), description, by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}