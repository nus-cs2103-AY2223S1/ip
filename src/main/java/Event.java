import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    String writeToFile() {
        return "E|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + time + ")";
    }
}