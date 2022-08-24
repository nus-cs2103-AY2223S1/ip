import java.io.FileWriter;
import java.io.IOException;

public class Events extends Task {

    private static final char SYMBOL = 'E';

    private String period;

    public Events(String s1, String s2) {
        super(s1);
        this.period = s2;
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format("E | %d | %s | %s\n", this.getDone(), this.getName(), this.period);
        fw.write(str);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.period);
    }
}
