package Models;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Duck.Duck;

public class Event extends Todo{
    private Date date;
    /**
     * Class that encapsulates an event object
     * It is a subclass of todo
     * @param title title encapsulates the title of the todo object - inherited
     * @param completed is a boolean value to keep track of the marked/unmarked status - inherited
     * @param date stores the date that the event is on
     */
    public Event(String title, boolean completed, Date date) {
        super(title, completed);
        this.date = date;
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("E;%s;%s;%d\n", this.title, Duck.dateToStringConverter(this.date), this.isCompleted ? 1 : 0));
    }

    public String toString() {
        return String.format("%s (at: %s)", super.toString().replace("[T]", "[E]"),
                new SimpleDateFormat("MMM dd yyyy HH:mm").format(this.date));
    }

    public void setDate(Date date){
        this.date = date;
    }
}
