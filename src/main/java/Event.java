import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Todo{
    private String date;
    public Event(String title, boolean completed,String date) {
        super(title, completed);
        this.date = date;
    }
    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("E;%s;%s;%d\n", this.title, this.date, this.completed ? 1 : 0));
    }

    public String toString() {
        return String.format("%s (at: %s)", super.toString().replace("[T]", "[E]"), this.date);
    }

}
