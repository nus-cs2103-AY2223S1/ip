import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task  {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    String writeToFile() {
        return "D|" + super.writeToFile();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + deadline + ")";
    }
}