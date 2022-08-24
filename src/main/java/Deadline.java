
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;

public class Deadline extends Todo{
    private Date date;
    public Deadline(String title, boolean completed, Date date) {
        super(title, completed);
        this.date = date;
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException, ParseException {
        writer.write(String.format("D;%s;%s;%d\n", this.title, Duck.dateToStringConverter(this.date), this.completed ? 1 : 0));
    }
    public String toString() {
        return String.format("%s (by: %s)", super.toString().replace("[T]", "[D]"),
                new SimpleDateFormat("MMM dd yyyy HH:mm").format(this.date));
    }
}
