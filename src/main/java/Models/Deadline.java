package Models;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import Duck.Duck;

public class Deadline extends Todo{
    private Date date;
    /**
     * Class that encapsulates an deadline object
     * It is a subclass of todo
     * @param title title encapsulates the title of the todo object - inherited
     * @param completed is a boolean value to keep track of the marked/unmarked status - inherited
     * @param date stores the date that the deadline must be done before
     */
    public Deadline(String title, boolean completed, Date date) {
        super(title, completed);
        this.date = date;
    }
    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("D;%s;%s;%d\n", this.title, Duck.dateToStringConverter(this.date), this.isCompleted ? 1 : 0));
    }
    public String toString() {
        return String.format("%s (by: %s)", super.toString().replace("[T]", "[D]"),
                new SimpleDateFormat("MMM dd yyyy HH:mm").format(this.date));
    }
    public void setDate(Date date){
        this.date = date;
    }
}
