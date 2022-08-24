<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Todo{
    private String date;
    public Event(String title, boolean completed,String date) {
        super(title, completed);
=======
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Event extends Todo{
    private Date date;
    public Event(String title, Date date) {
        super(title);
>>>>>>> branch-Level-8
        this.date = date;
    }
    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("E;%s;%s;%d\n", this.title, this.date, this.completed ? 1 : 0));
    }

    public String toString() {
        return String.format("%s (at: %s)", super.toString().replace("[T]", "[E]"),
                new SimpleDateFormat("MMM dd yyyy HH:mm").format(this.date));
    }

}
