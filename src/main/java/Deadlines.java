import java.io.FileWriter;
import java.io.IOException;

public class Deadlines extends Task {

    private static final char SYMBOL = 'D';

    private String deadline;

    public Deadlines(String s1, String s2){
        super(s1);
        this.deadline = s2;
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format("D | %d | %s | %s\n", this.getDone(), this.getName(), this.deadline);
        fw.write(str);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
