import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Todo{
    private String date;
    public Deadline(String title, boolean completed, String date) {
        super(title, completed);
        this.date = date;
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("D;%s;%s;%d\n", this.title, this.date, this.completed ? 1 : 0));
    }
    public String toString() {
        return String.format("%s (by: %s)", super.toString().replace("[T]", "[D]"), this.date);
    }
}
